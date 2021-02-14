package dev.daniel.viessmannnasa.data.repositories

import dev.daniel.viessmannnasa.data.model.Collection
import dev.daniel.viessmannnasa.data.model.Item
import dev.daniel.viessmannnasa.network.ImagesApi
import javax.inject.Inject

class DefaultNasaRepository @Inject constructor(
    private val api: ImagesApi
): NasaRepository {

    override suspend fun searchImages(query: String, page: Int): Collection {
        val apiResult = api.getImageResults(query, page)

        return apiResult.collection
    }

    override suspend fun getAssets(url: String): List<String> {
        return api.getAssets(url)
    }
}