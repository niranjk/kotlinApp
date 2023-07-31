package com.niranjankhatri.kotlinapp.catapi

import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.catapi.models.CatBreed
import com.niranjankhatri.kotlinapp.catapi.models.CatUiModel
import com.niranjankhatri.kotlinapp.catapi.models.Gender
import com.niranjankhatri.kotlinapp.catapi.models.ListItemUiModel
import com.niranjankhatri.kotlinapp.catapi.viewholder.ListItemViewHolder

private const val FEMALE_SYMBOL = "\u2640"
private const val MALE_SYMBOL = "\u2642"
private const val UNKNOWN_SYMBOL = "?"


interface ItemClickListener{
    fun onClickItem(catUiModel: CatUiModel)
}

class CatViewHolder(
    private val containerView: View,
    private val imageLoader: ImageLoader,
    private val onClickListener: ItemClickListener
): ListItemViewHolder(containerView)  {

    private val catBiographyView: TextView
            by lazy { containerView.findViewById(R.id.item_cat_biography) }
    private val catBreedView: TextView
            by lazy { containerView.findViewById(R.id.item_cat_breed) }
    private val catGenderView: TextView
            by lazy { containerView.findViewById(R.id.item_cat_gender) }
    private val catNameView: TextView
            by lazy { containerView.findViewById(R.id.item_cat_name) }
    private val catPhotoView: ImageView
            by lazy { containerView.findViewById(R.id.item_cat_photo) }

    override fun bindData(listItem: ListItemUiModel) {
        require(listItem is ListItemUiModel.Cat) { "Expected ListItemUiModel.Cat"}
        val catUiModel = listItem.data
        imageLoader.loadImage(catUiModel.imageUrl, catPhotoView)
        catNameView.text = catUiModel.name
        catBreedView.text = when(catUiModel.breed){
            CatBreed.AmericanCurl -> "American Curl"
            CatBreed.BalineseJavanese -> "Balinese-Javanese"
            CatBreed.ExoticShorthair -> "Exotic Shorthair"
        }
        catBiographyView.text = catUiModel.biography
        catGenderView.text = when (catUiModel.gender){
            Gender.Female -> FEMALE_SYMBOL
            Gender.Male -> MALE_SYMBOL
            Gender.Unknown -> UNKNOWN_SYMBOL
        }
        containerView.setOnClickListener {
            onClickListener.onClickItem(catUiModel)
        }
    }
}