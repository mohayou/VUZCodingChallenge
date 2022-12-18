package com.inmobiles.vuzcodingchallenge.repo

import androidx.lifecycle.LiveData
import com.inmobiles.vuzcodingchallenge.model.ImageResponse
import com.inmobiles.vuzcodingchallenge.model.ImageResult
import com.inmobiles.vuzcodingchallenge.model.LoginResponse
import com.inmobiles.vuzcodingchallenge.model.RegistrationResponse
import com.inmobiles.vuzcodingchallenge.util.Resource

interface MainRepositoryInterface {

    suspend fun inserImages(imageResults: List<ImageResult>)

    fun getImageFromDb(imageId:String) : LiveData<ImageResult>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>
    suspend fun login() : Resource<LoginResponse>
    suspend fun registration() : Resource<RegistrationResponse>

}