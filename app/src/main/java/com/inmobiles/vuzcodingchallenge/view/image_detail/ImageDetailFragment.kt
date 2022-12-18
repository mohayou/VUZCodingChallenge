package com.inmobiles.vuzcodingchallenge.view.image_detail

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.inmobiles.vuzcodingchallenge.R
import com.inmobiles.vuzcodingchallenge.databinding.FragmentImageDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class ImageDetailFragment @Inject constructor(private val glide: RequestManager) :
    Fragment(R.layout.fragment_image_detail) {

    lateinit var imageViewModel: ImageViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentImageDetailBinding = FragmentImageDetailBinding.inflate(inflater, container, false)
        imageViewModel = ViewModelProvider(requireActivity()).get(ImageViewModel::class.java)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.imageViewModel = imageViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            var imageId = ImageDetailFragmentArgs.fromBundle(it).imageId

            imageViewModel.getImageFromDb(imageId)

        }
    }

    @BindingAdapter("loadLargeImage")
    fun loadLargeImage(ivRowImage: ImageView, url: String) {
        url?.let {
            glide.load(url).into(ivRowImage)
        }
    }
}