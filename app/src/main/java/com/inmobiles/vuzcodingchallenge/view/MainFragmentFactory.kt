package com.inmobiles.vuzcodingchallenge.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.inmobiles.vuzcodingchallenge.view.adapter.ImageRecyclerViewAdapter
import com.inmobiles.vuzcodingchallenge.view.get_image.GetImagesFragment
import com.inmobiles.vuzcodingchallenge.view.image_detail.ImageDetailFragment
import javax.inject.Inject

class MainFragmentFactory @Inject constructor(
    private val imageRecyclerAdapter: ImageRecyclerViewAdapter,
    private val glide : RequestManager,
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when(className){
            GetImagesFragment::class.java.name -> GetImagesFragment(imageRecyclerAdapter)
            ImageDetailFragment::class.java.name -> ImageDetailFragment(glide)
            else -> super.instantiate(classLoader, className)
        }
    }
}