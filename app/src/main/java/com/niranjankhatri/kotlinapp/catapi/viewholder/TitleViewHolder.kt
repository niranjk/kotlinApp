package com.niranjankhatri.kotlinapp.catapi.viewholder

import android.view.View
import android.widget.TextView
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.catapi.models.ListItemUiModel

class TitleViewHolder(
    containerView: View
) : ListItemViewHolder(containerView){

    private val titleView: TextView by lazy {
        containerView.findViewById(R.id.item_title_title)
    }

    override fun bindData(listItem: ListItemUiModel) {
        require(listItem is ListItemUiModel.Title) {
            "Expected ListItemUiModel.Title"
        }
        titleView.text = listItem.title
    }
}