package dev.daniel.viessmannnasa.data.repositories

import dev.daniel.viessmannnasa.data.model.Collection
import dev.daniel.viessmannnasa.data.model.Item

interface NasaRepository {

    suspend fun searchImages(query: String, page: Int): Collection

    suspend fun getAssets(url: String): List<String>

}