package com.niranjankhatri.kotlinapp.catapi.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.niranjankhatri.kotlinapp.catapi.models.ListItemUiModel

abstract class ListItemViewHolder(
    private val containerView: View
) : RecyclerView.ViewHolder(containerView){
    abstract fun bindData(listItem: ListItemUiModel)
}