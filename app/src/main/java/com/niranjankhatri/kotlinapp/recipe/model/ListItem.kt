package com.niranjankhatri.kotlinapp.recipe.model

interface ListItem

data class TitleUiModel(val title: String): ListItem
data class RecipeUiModel(val title: String, val desciption: String, val flavor: Flavor): ListItem

enum class Flavor {
    SAVORY,
    SWEET
}