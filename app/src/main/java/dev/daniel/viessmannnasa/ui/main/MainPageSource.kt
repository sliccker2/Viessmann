package dev.daniel.viessmannnasa.ui.main

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.daniel.viessmannnasa.data.model.Item
import dev.daniel.viessmannnasa.data.repositories.NasaRepository
import retrofit2.HttpException
import java.io.IOException

class MainPageSource(
    val query: String,
    val nasaRepository: NasaRepository
) : PagingSource<Int, Item>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Item> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = nasaRepository.searchImages(query, nextPageNumber)

            val nextPage =
                response.links?.find { n -> n.rel == "next" }?.href?.substringAfter("page=")?.substringBefore("&")?.toInt()
            val prevPage =
                response.links?.find { n -> n.rel == "prev" }?.href?.substringAfter("page=")?.substringBefore("&")?.toInt()

            return LoadResult.Page(
                data = response.items ?: emptyList(),
                prevKey = prevPage,
                nextKey = nextPage
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Item>): Int? {
        return state.anchorPosition
    }
}