package com.inmobiles.vuzcodingchallenge.view.get_image

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inmobiles.vuzcodingchallenge.model.ImageResponse
import com.inmobiles.vuzcodingchallenge.model.ImageResult
import com.inmobiles.vuzcodingchallenge.repo.MainRepositoryInterface
import com.inmobiles.vuzcodingchallenge.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SearchImageViewModel @Inject constructor(
        private val repository : MainRepositoryInterface
) : ViewModel() {

        private val images = MutableLiveData<Resource<ImageResponse>>()
        val imageList : LiveData<Resource<ImageResponse>>
                get() = images

        fun searchForImage () {
                images.value = Resource.loading(null)
                viewModelScope.launch {
                        val response = repository.searchImage("")
                        images.value = response
                }
        }

        fun insertImages(imageList:List<ImageResult>)
        {
                viewModelScope.launch {
                        repository.inserImages(imageList)
                }
        }


}