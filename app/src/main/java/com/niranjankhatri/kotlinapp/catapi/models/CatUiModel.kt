package com.niranjankhatri.kotlinapp.catapi.models

data class CatUiModel(
    val gender: Gender,
    val breed: CatBreed,
    val name: String,
    val biography: String,
    val imageUrl : String
)
