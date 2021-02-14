package dev.daniel.viessmannnasa.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.daniel.viessmannnasa.R
import dev.daniel.viessmannnasa.databinding.ActivityMainBinding
import dev.daniel.viessmannnasa.ui.largeImage.LargeImageFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val viewModel: MainViewModel by viewModels()

    lateinit var adapter: MainAdapter

    private var searchJob: Job? = null

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchNasa(query).collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun search() {
        if(!binding.searchQuery.text.isNullOrBlank())
            search(binding.searchQuery.text.toString())
    }


    @FlowPreview
    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.searchQuery.setOnEditorActionListener { tv, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                search()
                true
            } else {
                false
            }
        }

        binding.searchQuery.setOnKeyListener { tv, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                search()
                true
            } else {
                false
            }
        }


        binding.lifecycleOwner = this

        adapter = MainAdapter(viewModel)

        binding.rvImageList.adapter = adapter

        viewModel.displayLargeImage.observe(this) {
            supportFragmentManager.beginTransaction().add(
                R.id.frame_large_image, LargeImageFragment().apply {
                    arguments = it
                }
            ).addToBackStack(LargeImageFragment::class.java.simpleName).commit()
        }

    }
}