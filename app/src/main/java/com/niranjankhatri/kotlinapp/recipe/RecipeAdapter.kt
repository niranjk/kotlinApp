package com.niranjankhatri.kotlinapp.recipe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.recipe.model.Flavor
import com.niranjankhatri.kotlinapp.recipe.model.ListItem
import com.niranjankhatri.kotlinapp.recipe.model.RecipeUiModel
import com.niranjankhatri.kotlinapp.recipe.model.TitleUiModel

private const val VIEW_TYPE_TITLE = 0
private const val VIEW_TYPE_RECIPE = 1

class RecipeAdapter(
    private val layoutInflater: LayoutInflater,
    private val onClickListener: OnClickListener
): RecyclerView.Adapter<BaseViewHolder>(){

    private val savoryTitle = TitleUiModel("Savory")
    private val sweetTitle = TitleUiModel("Sweet")
    val listItems = mutableListOf<ListItem>(savoryTitle, sweetTitle)

    val swipeToDeleteCallback = SwipeToDeleteCallback()

    // add Recipe
    fun addRecipe(recipeUiModel: RecipeUiModel){
        val insertionIndex = listItems.indexOf(when (recipeUiModel.flavor){
            Flavor.SAVORY -> savoryTitle
             Flavor.SWEET -> sweetTitle
        }) + 1
        listItems.add(insertionIndex, recipeUiModel)
        notifyItemInserted(insertionIndex)
    }

    // remove Recipe
    fun removeRecipe(position: Int){
        listItems.removeAt(position)
        notifyItemRemoved(position)
    }

    override fun getItemViewType(position: Int): Int {
        return when(listItems[position]){
            is TitleUiModel -> VIEW_TYPE_TITLE
            is RecipeUiModel -> VIEW_TYPE_RECIPE
            else -> throw java.lang.IllegalStateException("Unexpected data type at $position")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
       return when(viewType){
           VIEW_TYPE_TITLE -> TitleViewHolder(
               layoutInflater.inflate(R.layout.item_title, parent, false)
           )
           VIEW_TYPE_RECIPE -> RecipeViewHolder(
               layoutInflater.inflate(R.layout.item_recipe, parent, false),
               object : RecipeViewHolder.OnClickListener {
                   override fun onClick(recipeUiModel: RecipeUiModel) {
                       onClickListener.onItemClick(recipeUiModel)
                   }
               }
           )
           else -> throw java.lang.IllegalStateException("Unexpected View Type $viewType")
       }
    }

    override fun getItemCount(): Int {
       return listItems.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bindData(listItems[position])
    }

    interface OnClickListener{
        fun onItemClick(recipeUiModel: RecipeUiModel)
    }

    inner class SwipeToDeleteCallback : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int {
            return if (viewHolder is RecipeViewHolder){
                makeMovementFlags(
                    ItemTouchHelper.ACTION_STATE_IDLE,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                ) or makeMovementFlags(
                    ItemTouchHelper.ACTION_STATE_SWIPE,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
                )
            } else {
                0
            }
        }
        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            removeRecipe(position)
        }

    }
}