package dev.daniel.viessmannnasa.ui.main

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.daniel.viessmannnasa.SingleLiveEvent
import dev.daniel.viessmannnasa.data.model.Item
import dev.daniel.viessmannnasa.data.repositories.NasaRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val nasaRepository: NasaRepository
) : ViewModel() {

    val displayLargeImage = SingleLiveEvent<Bundle>()

    fun searchNasa(query: String): Flow<PagingData<Item>> {
        return Pager(
            // Configure how data is loaded by passing additional properties to
            // PagingConfig, such as prefetchDistance.
            PagingConfig(pageSize = 20)
        ) {
            MainPageSource(query, nasaRepository)
        }.flow
            .cachedIn(viewModelScope)
    }

    fun showLarge(link: String, desc: String, assetsUrl: String) = viewModelScope.launch {


        val assets = nasaRepository.getAssets(assetsUrl)

        if(assets.contains("orig"))
            link.replace("thumb", "large")
        else if(assets.contains("large"))
            link.replace("thumb", "orig")
        else if(assets.contains("small"))
            link.replace("thumb", "small")

        val bundle = Bundle().apply {
            this.putString("link", link)
            this.putString("desc", desc)
        }

        displayLargeImage.postValue(bundle)
    }
}