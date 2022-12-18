package com.inmobiles.vuzcodingchallenge.view.get_image

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.inmobiles.vuzcodingchallenge.R
import com.inmobiles.vuzcodingchallenge.view.adapter.ImageRecyclerViewAdapter
import com.inmobiles.vuzcodingchallenge.util.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_get_images.*
import javax.inject.Inject

@AndroidEntryPoint
class GetImagesFragment @Inject constructor(
    val imageRecyclerAdapter: ImageRecyclerViewAdapter
) : Fragment(R.layout.fragment_get_images) {

    lateinit var searchViewModel: SearchImageViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      searchViewModel =    ViewModelProvider(requireActivity()).get(SearchImageViewModel::class.java)



        rvResults.apply {
            adapter = imageRecyclerAdapter
            layoutManager = LinearLayoutManager(context)
        }

        imageRecyclerAdapter.setOnItemClickListener {
//            showConfirmationDialog(it)
            findNavController().navigate(GetImagesFragmentDirections.actionGetImagesFragmentToImageDetailFragment(it))

        }

        performSearch()
    }

//    private fun showConfirmationDialog(id:String) {
//        val builder = AlertDialog.Builder(context)
//        builder.setTitle(resources.getString(R.string.image_details))
//        builder.setMessage(resources.getString(R.string.show_details))
//        builder.setPositiveButton(resources.getString(R.string.ok)) { dialog, which ->
//            findNavController().navigate(
//                SearchFragmentDirections.actionSearchFragmentToImageDetailFragment(id)
//            )
//            dialog.dismiss()
//        }
//
//        builder.setNegativeButton(resources.getString(R.string.no)) { dialog, which ->
//            dialog.dismiss()
//        }
//        builder.show()
//
//    }

    private fun performSearch() {
            searchViewModel.searchForImage()


        searchViewModel.imageList.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.hits?.let {
                        imageRecyclerAdapter.images = it
                        searchViewModel.insertImages(it)
                    }
                }

                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message ?: "Error", Toast.LENGTH_LONG)
                        .show()
                }

                Status.LOADING -> {

                }
            }
        }
    }
}