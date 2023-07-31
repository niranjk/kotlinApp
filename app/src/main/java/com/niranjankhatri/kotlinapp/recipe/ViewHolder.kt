package com.niranjankhatri.kotlinapp.recipe

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.recipe.model.ListItem
import com.niranjankhatri.kotlinapp.recipe.model.RecipeUiModel
import com.niranjankhatri.kotlinapp.recipe.model.TitleUiModel

abstract class BaseViewHolder(
    containerView: View
): RecyclerView.ViewHolder(containerView){
    abstract fun bindData(listItem: ListItem)
}

class TitleViewHolder(containerView: View): BaseViewHolder(containerView){
    private val titleView: TextView by lazy {
        containerView.findViewById(R.id.item_title_title)
    }

    override fun bindData(listItem: ListItem) {
        titleView.text = (listItem as TitleUiModel).title
    }
}

class RecipeViewHolder(
    containerView: View,
    private val onClickListener: OnClickListener
): BaseViewHolder(containerView){
    private val titleView: TextView by lazy {
        containerView.findViewById(R.id.recipe_title)
    }

    override fun bindData(listItem: ListItem) {
        titleView.text = (listItem as RecipeUiModel).title
        titleView.setOnClickListener {
            onClickListener.onClick(listItem)
        }
    }

    interface OnClickListener{
        fun onClick(recipeUiModel: RecipeUiModel)
    }
}