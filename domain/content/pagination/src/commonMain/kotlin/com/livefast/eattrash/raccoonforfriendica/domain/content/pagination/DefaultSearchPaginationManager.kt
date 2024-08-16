package com.livefast.eattrash.raccoonforfriendica.domain.content.pagination

import com.livefast.eattrash.raccoonforfriendica.domain.content.data.ExploreItemModel
import com.livefast.eattrash.raccoonforfriendica.domain.content.data.SearchResultType
import com.livefast.eattrash.raccoonforfriendica.domain.content.repository.SearchRepository

internal class DefaultSearchPaginationManager(
    private val searchRepository: SearchRepository,
) : SearchPaginationManager {
    private var specification: SearchPaginationSpecification? = null
    override var canFetchMore: Boolean = true
    private var pageCursor: String? = null
    private val history = mutableListOf<ExploreItemModel>()

    override suspend fun reset(specification: SearchPaginationSpecification) {
        this.specification = specification
        pageCursor = null
        history.clear()
        canFetchMore = true
    }

    override suspend fun loadNextPage(): List<ExploreItemModel> {
        val specification = this.specification ?: return emptyList()
        val results =
            when (specification) {
                is SearchPaginationSpecification.Entries ->
                    searchRepository.search(
                        query = specification.query,
                        pageCursor = pageCursor,
                        type = SearchResultType.Entries,
                    )

                is SearchPaginationSpecification.Hashtags ->
                    searchRepository.search(
                        query = specification.query,
                        pageCursor = pageCursor,
                        type = SearchResultType.Hashtags,
                    )

                is SearchPaginationSpecification.Users ->
                    searchRepository.search(
                        query = specification.query,
                        pageCursor = pageCursor,
                        type = SearchResultType.Users,
                    )
            }.deduplicate()

        if (results.isNotEmpty()) {
            pageCursor = results.last().id
            canFetchMore = true
        } else {
            canFetchMore = false
        }

        history.addAll(results)

        // return an object containing copies
        return history.map { it }
    }

    private fun List<ExploreItemModel>.deduplicate(): List<ExploreItemModel> =
        filter { e1 ->
            history.none { e2 -> e1.id == e2.id }
        }
}