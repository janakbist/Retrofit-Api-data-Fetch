package com.plcoding.retrofitcrashcourse


import com.google.gson.annotations.SerializedName

data class Todo(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int,
    val name : String,
    val image: Image,

)
data class Image(
    val height: Int,
    val id: String,
    var url: String,
    val width: Int
)