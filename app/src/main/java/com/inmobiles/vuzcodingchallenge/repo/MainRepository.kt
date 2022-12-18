package com.inmobiles.vuzcodingchallenge.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.inmobiles.vuzcodingchallenge.api.RetrofitAPI
import com.inmobiles.vuzcodingchallenge.model.ImageResponse
import com.inmobiles.vuzcodingchallenge.model.ImageResult
import com.inmobiles.vuzcodingchallenge.model.LoginResponse
import com.inmobiles.vuzcodingchallenge.model.RegistrationResponse
import com.inmobiles.vuzcodingchallenge.roomdb.ImageDao
import com.inmobiles.vuzcodingchallenge.util.Resource
import java.lang.Exception
import javax.inject.Inject

class MainRepository @Inject constructor (
    private val artDao : ImageDao,
    private val retrofitApi : RetrofitAPI
) : MainRepositoryInterface {

    override suspend fun inserImages(imageResults: List<ImageResult>) {
        artDao.insertImages(imageResults)
    }

    override fun getImageFromDb(imageId:String): LiveData<ImageResult> {
        return artDao.getImageFromDb(imageId)
    }

    override suspend fun searchImage(imageString: String): Resource<ImageResponse> {
        return try {
            val response = retrofitApi.imageSearch(imageString)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("",null)
                } else {
                    Resource.error("",null)
            }
        } catch (e: Exception) {
            Log.d("testEception",e.toString())
            Resource.error("No data!",null)
        }
    }

    override suspend fun login(): Resource<LoginResponse> = Resource.success(LoginResponse(true))

    override suspend fun registration(): Resource<RegistrationResponse> =Resource.success(RegistrationResponse(true))


}