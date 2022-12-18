package com.inmobiles.vuzcodingchallenge.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inmobiles.vuzcodingchallenge.model.ImageResult
import com.inmobiles.vuzcodingchallenge.roomdb.ImageDao

@Database(entities = [ImageResult::class],version = 1,exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {
    abstract fun artDao() : ImageDao
}
