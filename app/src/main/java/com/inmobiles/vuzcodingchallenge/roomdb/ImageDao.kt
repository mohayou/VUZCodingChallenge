package com.inmobiles.vuzcodingchallenge.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*
import com.inmobiles.vuzcodingchallenge.model.ImageResult

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImages(imageResults: List<ImageResult>)

    @Query("SELECT * FROM imageResult WHERE id =:imageId")
    fun getImageFromDb(imageId:String): LiveData<ImageResult>

}