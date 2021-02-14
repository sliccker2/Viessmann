package dev.daniel.viessmannnasa.network

import android.graphics.Bitmap
import dev.daniel.viessmannnasa.data.model.ImageResult
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ImagesApi {

    @GET("search")
    suspend fun getImageResults(@Query("keywords") query: String, @Query("page") page: Int): ImageResult

    @GET
    suspend fun getAssets(@Url url: String): List<String>

}