package com.niranjankhatri.kotlinapp.catapi.models

sealed class ListItemUiModel {
    data class Title(val title: String) : ListItemUiModel()
    data class Cat(val data: CatUiModel): ListItemUiModel()
}