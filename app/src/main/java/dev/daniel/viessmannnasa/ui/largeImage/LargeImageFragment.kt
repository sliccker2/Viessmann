package dev.daniel.viessmannnasa.ui.largeImage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dev.daniel.viessmannnasa.databinding.FragmentImageLargeBinding


class LargeImageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = FragmentImageLargeBinding.inflate(inflater)

        val link = arguments?.getString("link")
        val desc = arguments?.getString("desc")

        link?.let {
            view.link = it
        }

        desc?.let {
            view.desc = desc
        }


        return view.root
    }

}