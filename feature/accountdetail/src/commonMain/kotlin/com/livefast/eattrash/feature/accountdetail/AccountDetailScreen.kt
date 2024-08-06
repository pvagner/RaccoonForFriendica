package com.livefast.eattrash.feature.accountdetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
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
import cafe.adriel.voyager.koin.getScreenModel
import com.livefast.eattrash.feature.accountdetail.composable.AccountFields
import com.livefast.eattrash.feature.accountdetail.composable.AccountHeader
import com.livefast.eattrash.raccoonforfriendica.core.appearance.theme.Spacing
import com.livefast.eattrash.raccoonforfriendica.core.commonui.components.SectionSelector
import com.livefast.eattrash.raccoonforfriendica.core.commonui.content.TimelineItem
import com.livefast.eattrash.raccoonforfriendica.core.l10n.messages.LocalStrings
import com.livefast.eattrash.raccoonforfriendica.core.navigation.di.getDetailOpener
import com.livefast.eattrash.raccoonforfriendica.core.navigation.di.getNavigationCoordinator
import com.livefast.eattrash.raccoonforfriendica.core.utils.datetime.prettifyDate
import com.livefast.eattrash.raccoonforfriendica.core.utils.di.getUrlManager
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.FieldModel
import org.koin.core.parameter.parametersOf

class AccountDetailScreen(
    private val id: String,
) : Screen {
    @OptIn(
        ExperimentalMaterial3Api::class,
        ExperimentalFoundationApi::class,
        ExperimentalMaterialApi::class,
    )
    @Composable
    override fun Content() {
        val model = getScreenModel<AccountDetailMviModel>(parameters = { parametersOf(id) })
        val uiState by model.uiState.collectAsState()
        val topAppBarState = rememberTopAppBarState()
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(topAppBarState)
        val navigationCoordinator = remember { getNavigationCoordinator() }
        val uriHandler = LocalUriHandler.current
        val urlManager = remember { getUrlManager(uriHandler) }
        val detailOpener = remember { getDetailOpener() }

        Scaffold(
            topBar = {
                TopAppBar(
                    scrollBehavior = scrollBehavior,
                    title = {
                        Text(
                            modifier = Modifier.padding(horizontal = Spacing.s),
                            text = uiState.account?.handle.orEmpty(),
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
            content = { padding ->
                val pullRefreshState =
                    rememberPullRefreshState(
                        refreshing = uiState.refreshing,
                        onRefresh = {
                            model.reduce(AccountDetailMviModel.Intent.Refresh)
                        },
                    )
                Box(
                    modifier =
                        Modifier
                            .padding(padding)
                            .nestedScroll(scrollBehavior.nestedScrollConnection)
                            .pullRefresh(pullRefreshState),
                ) {
                    LazyColumn {
                        if (uiState.account != null) {
                            item {
                                AccountHeader(
                                    account = uiState.account,
                                    onOpenUrl = { url ->
                                        urlManager.open(url)
                                    },
                                )
                            }
                        }
                        item {
                            AccountFields(
                                modifier =
                                    Modifier.padding(
                                        top = Spacing.m,
                                        bottom = Spacing.s,
                                    ),
                                fields =
                                    buildList {
                                        uiState.account?.created?.also { date ->
                                            add(
                                                FieldModel(
                                                    key = LocalStrings.current.accountAge,
                                                    value = date.prettifyDate(),
                                                ),
                                            )
                                        }
                                        addAll(uiState.account?.fields.orEmpty())
                                    },
                                onOpenUrl = { url ->
                                    urlManager.open(url)
                                },
                            )
                        }
                        if (uiState.account != null) {
                            stickyHeader {
                                SectionSelector(
                                    modifier = Modifier.padding(bottom = Spacing.s),
                                    titles =
                                        listOf(
                                            AccountSection.Posts.toReadableName(),
                                            AccountSection.All.toReadableName(),
                                            AccountSection.Pinned.toReadableName(),
                                            AccountSection.Media.toReadableName(),
                                        ),
                                    currentSection = uiState.section.toInt(),
                                    onSectionSelected = {
                                        val section = it.toAccountSection()
                                        model.reduce(
                                            AccountDetailMviModel.Intent.ChangeSection(
                                                section,
                                            ),
                                        )
                                    },
                                )
                            }
                        }
                        items(
                            items = uiState.entries,
                            key = { it.id },
                        ) { entry ->
                            TimelineItem(
                                entry = entry,
                                onClick = {
                                    detailOpener.openEntryDetail(entry.id)
                                },
                                onOpenUrl = { url ->
                                    urlManager.open(url)
                                },
                                onOpenUser = {
                                    detailOpener.openAccountDetail(it.id)
                                },
                            )
                            HorizontalDivider(
                                modifier = Modifier.padding(vertical = Spacing.s),
                            )
                        }

                        item {
                            if (!uiState.initial && !uiState.loading && uiState.canFetchMore) {
                                model.reduce(AccountDetailMviModel.Intent.LoadNextPage)
                            }
                            if (uiState.loading) {
                                Box(
                                    modifier = Modifier.fillMaxWidth(),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
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