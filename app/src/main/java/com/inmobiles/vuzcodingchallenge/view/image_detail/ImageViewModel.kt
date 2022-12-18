package com.inmobiles.vuzcodingchallenge.view.image_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.inmobiles.vuzcodingchallenge.model.ImageResult
import com.inmobiles.vuzcodingchallenge.repo.MainRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val repository : MainRepositoryInterface
) : ViewModel() {

    var imagedetails: LiveData<ImageResult> = MutableLiveData<ImageResult>()

    //    fun getImageFromDb(imageId:String):LiveData<ImageResult> = repository.getImageFromDb(imageId)
    fun getImageFromDb(imageId: String) {
        imagedetails =  repository.getImageFromDb(imageId)
}
}