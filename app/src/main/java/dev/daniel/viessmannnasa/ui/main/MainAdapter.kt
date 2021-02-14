package dev.daniel.viessmannnasa.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.daniel.viessmannnasa.data.model.Item
import dev.daniel.viessmannnasa.databinding.ImageItemBinding

class MainAdapter(private val mainViewModel: MainViewModel) : PagingDataAdapter<Item, MainAdapter.ViewHolder>(ItemDiffUtil()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
         val inflater = LayoutInflater.from(parent.context)
        val binding = ImageItemBinding.inflate(inflater)

        return ViewHolder(binding)
    }

    inner class ViewHolder( private val dataBinding: ImageItemBinding): RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(item: Item) {
            dataBinding.info = item.data[0]
            dataBinding.link = item.links[0]
            dataBinding.assetsUrl = item.href
            dataBinding.viewmodel = mainViewModel
        }
    }

}

class ItemDiffUtil : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.data[0].nasa_id == newItem.data[0].nasa_id
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.data[0] == newItem.data[0]
    }

}