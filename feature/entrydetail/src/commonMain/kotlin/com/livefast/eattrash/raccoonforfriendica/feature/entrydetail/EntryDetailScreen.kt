package com.livefast.eattrash.raccoonforfriendica.feature.entrydetail

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Reply
import androidx.compose.material.icons.filled.Reply
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextOverflow
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.getScreenModel
import com.livefast.eattrash.raccoonforfriendica.core.appearance.theme.Spacing
import com.livefast.eattrash.raccoonforfriendica.core.commonui.components.di.getFabNestedScrollConnection
import com.livefast.eattrash.raccoonforfriendica.core.commonui.content.TimelineItem
import com.livefast.eattrash.raccoonforfriendica.core.commonui.content.TimelineItemPlaceholder
import com.livefast.eattrash.raccoonforfriendica.core.commonui.content.safeKey
import com.livefast.eattrash.raccoonforfriendica.core.l10n.messages.LocalStrings
import com.livefast.eattrash.raccoonforfriendica.core.navigation.di.getDetailOpener
import com.livefast.eattrash.raccoonforfriendica.core.navigation.di.getNavigationCoordinator
import com.livefast.eattrash.raccoonforfriendica.domain.identity.usecase.di.getOpenUrlUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.core.parameter.parametersOf

class EntryDetailScreen(
    private val id: String,
) : Screen {
    override val key: ScreenKey
        get() = super.key + id

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        val model = getScreenModel<EntryDetailMviModel>(parameters = { parametersOf(id) })
        val uiState by model.uiState.collectAsState()
        val topAppBarState = rememberTopAppBarState()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
        val navigationCoordinator = remember { getNavigationCoordinator() }
        val uriHandler = LocalUriHandler.current
        val openUrl = remember { getOpenUrlUseCase(uriHandler) }
        val detailOpener = remember { getDetailOpener() }
        val lazyListState = rememberLazyListState()
        val fabNestedScrollConnection = remember { getFabNestedScrollConnection() }
        val isFabVisible by fabNestedScrollConnection.isFabVisible.collectAsState()

        LaunchedEffect(model) {
            model.effects
                .onEach { event ->
                    when (event) {
                        is EntryDetailMviModel.Effect.ScrollToItem ->
                            runCatching {
                                lazyListState.scrollToItem(event.index)
                            }
                    }
                }.launchIn(this)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    scrollBehavior = scrollBehavior,
                    title = {
                        Text(
                            text =
                                buildString {
                                    append(LocalStrings.current.postTitleBy)
                                    append(" ")
                                    append(uiState.creator?.username.orEmpty())
                                },
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    },
                    navigationIcon = {
                        if (navigationCoordinator.canPop.value) {
                            Image(
                                modifier =
                                    Modifier.clickable {
                                        navigationCoordinator.pop()
                                    },
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                            )
                        }
                    },
                )
            },
            floatingActionButton = {
                if (uiState.isLogged) {
                    AnimatedVisibility(
                        visible = isFabVisible,
                        enter =
                            slideInVertically(
                                initialOffsetY = { it * 2 },
                            ),
                        exit =
                            slideOutVertically(
                                targetOffsetY = { it * 2 },
                            ),
                    ) {
                        FloatingActionButton(
                            onClick = {
                                val entry = uiState.entries.firstOrNull { it.id == id }
                                if (entry != null) {
                                    detailOpener.openComposer(
                                        inReplyToId = id,
                                        inReplyToUsername = entry.creator?.username,
                                        inReplyToHandle = entry.creator?.handle,
                                    )
                                }
                            },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.Reply,
                                contentDescription = null,
                            )
                        }
                    }
                }
            },
            content = { padding ->
                val pullRefreshState =
                    rememberPullRefreshState(
                        refreshing = uiState.refreshing,
                        onRefresh = {
                            model.reduce(EntryDetailMviModel.Intent.Refresh)
                        },
                    )
                Box(
                    modifier =
                        Modifier
                            .padding(padding)
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                            .nestedScroll(fabNestedScrollConnection)
                            .pullRefresh(pullRefreshState),
                ) {
                    LazyColumn(
                        state = lazyListState,
                    ) {
                        if (uiState.initial) {
                            items(5) {
                                TimelineItemPlaceholder(modifier = Modifier.fillMaxWidth())
                                HorizontalDivider(
                                    modifier = Modifier.padding(vertical = Spacing.s),
                                )
                            }
                        }

                        items(
                            items = uiState.entries,
                            key = { it.safeKey },
                        ) { entry ->
                            TimelineItem(
                                entry = entry,
                                extendedSocialInfoEnabled = (entry.id == id),
                                blurNsfw = uiState.blurNsfw,
                                onOpenUrl = { url ->
                                    openUrl(url)
                                },
                                onClick = { e ->
                                    if (e.id != id) {
                                        detailOpener.openEntryDetail(entry.id)
                                    }
                                },
                                onOpenUser = {
                                    detailOpener.openUserDetail(it.id)
                                },
                                onReblog = { e ->
                                    model.reduce(EntryDetailMviModel.Intent.ToggleReblog(e))
                                },
                                onBookmark = { e ->
                                    model.reduce(EntryDetailMviModel.Intent.ToggleBookmark(e))
                                },
                                onFavorite = { e ->
                                    model.reduce(EntryDetailMviModel.Intent.ToggleFavorite(e))
                                },
                                onOpenUsersFavorite = { e ->
                                    detailOpener.openEntryUsersFavorite(
                                        entryId = e.id,
                                        count = e.favoriteCount,
                                    )
                                },
                                onOpenUsersReblog = { e ->
                                    detailOpener.openEntryUsersReblog(
                                        entryId = e.id,
                                        count = e.reblogCount,
                                    )
                                },
                                onReply = { e ->
                                    detailOpener.openComposer(
                                        inReplyToId = e.id,
                                        inReplyToHandle = e.creator?.handle,
                                        inReplyToUsername =
                                            e.creator?.let {
                                                it.displayName ?: it.username
                                            },
                                    )
                                },
                            )
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = Spacing.s),
                            )
                        }
                        item {
                            Spacer(modifier = Modifier.height(Spacing.xxxl))
                        }
                    }

                    PullRefreshIndicator(
                        refreshing = uiState.refreshing,
                        state = pullRefreshState,
                        modifier = Modifier.align(Alignment.TopCenter),
                        backgroundColor = MaterialTheme.colorScheme.background,
                        contentColor = MaterialTheme.colorScheme.onBackground,
                    )
                }
            },
        )
    }
}
