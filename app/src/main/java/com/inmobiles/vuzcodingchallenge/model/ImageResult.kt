package com.inmobiles.vuzcodingchallenge.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity(tableName = "imageResult")
data class ImageResult(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val pageURL: String?,
    val tags: String?,
    val previewURL: String?,
    val previewWidth: String?,
    val previewHeight: String?,
    val webformatURL: String?,
    val webformatWidth: String?,
    val webformatHeight: String?,
    val largeImageURL: String?,
    val imageWidth: String?,
    val imageHeight: String?,
    val imageSize: String?,
    val views: String?,
    val downloads: String?,
    val collections: String?,
    val likes: String?,
    val comments: String?,
    val type: String?,

    @SerializedName("user_id")
    val userID: String?,

    val user: String?,
    val userImageURL: String?
):Serializable
