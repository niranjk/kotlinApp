package com.niranjankhatri.kotlinapp.catapi

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.catapi.models.CatUiModel
import com.niranjankhatri.kotlinapp.catapi.models.ListItemUiModel

interface OnClickListener {
    fun onItemClick(catUiModel: CatUiModel)
}

class CatAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageLoader: ImageLoader,
    private val onClickListener: OnClickListener
): RecyclerView.Adapter<CatViewHolder>() {

    private val catList = mutableListOf<CatUiModel>()

    fun setData(newCatList: List<CatUiModel>){
        catList.clear()
        catList.addAll(newCatList)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        val view = layoutInflater.inflate(R.layout.item_cat, parent, false)
        return CatViewHolder(view, imageLoader,
            object : ItemClickListener {
                override fun onClickItem(catUiModel: CatUiModel) {
                    return onClickListener.onItemClick(catUiModel)
                }
            }
        )
    }

    override fun getItemCount(): Int {
        return catList.size
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
    /*
    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        require(catList is CatUiModel)
       holder.bindData(catList[position])
    }

     */

}