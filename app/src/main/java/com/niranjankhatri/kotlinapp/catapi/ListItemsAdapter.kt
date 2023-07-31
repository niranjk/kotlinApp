package com.niranjankhatri.kotlinapp.catapi

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.catapi.models.CatUiModel
import com.niranjankhatri.kotlinapp.catapi.models.ListItemUiModel
import com.niranjankhatri.kotlinapp.catapi.viewholder.ListItemViewHolder
import com.niranjankhatri.kotlinapp.catapi.viewholder.TitleViewHolder

private const val VIEW_TYPE_GROUP = 0
private const val VIEW_TYPE_CAT = 1

interface ListItemOnClickListener {
    fun onItemClick(catUiModel: CatUiModel)
}

class ListItemsAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: ListItemOnClickListener
): RecyclerView.Adapter<ListItemViewHolder>() {

    private val dataList = mutableListOf<ListItemUiModel>()

    // expose the instance here
    val swipeToDeleteCallback = SwipeToDeleteCallback()

    fun setData(newdataList: List<ListItemUiModel>){
        dataList.clear()
        dataList.addAll(newdataList)
        notifyDataSetChanged()
    }

    // Deletion
    fun removeItem(position: Int){
        dataList.removeAt(position)
        notifyItemRemoved(position)
    }

    // Addition
    fun addItem(position: Int, item: ListItemUiModel){
        dataList.add(position, item)
        notifyItemInserted(position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bindData(dataList[position])
    }

    override fun getItemViewType(position: Int): Int {
        return when(dataList[position]){
            is ListItemUiModel.Title -> VIEW_TYPE_GROUP
            is ListItemUiModel.Cat -> VIEW_TYPE_CAT
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
       return  when(viewType){
           VIEW_TYPE_CAT -> {
               val view = layoutInflater.inflate(R.layout.item_cat, parent, false)
               CatViewHolder(view, imageLoader,
                   object : ItemClickListener {
                       override fun onClickItem(catUiModel: CatUiModel) {
                           return onClickListener.onItemClick(catUiModel)
                       }
                   }
               )
           }
           VIEW_TYPE_GROUP -> {
               val view = layoutInflater.inflate(R.layout.item_title, parent, false)
               TitleViewHolder(
                   view
               )
           }
           else -> throw java.lang.IllegalArgumentException("Unknown View Type requested: $viewType")
       }
    }

    inner class SwipeToDeleteCallback:
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        // tells ItemTouchHelper which items can be swiped
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return if (viewHolder is CatViewHolder){
                makeMovementFlags(ItemTouchHelper.ACTION_STATE_IDLE, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) or makeMovementFlags(
                    ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                )
            } else {
                0 // neither swiping nor moving will occur
            }
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            // we swipe to delete
            val position = viewHolder.adapterPosition
            removeItem(position)
        }

    }
}