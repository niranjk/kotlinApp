package com.niranjankhatri.kotlinapp.catapi

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.catapi.models.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/***
 *
 * 1. Retrofit - OKHttp and better for handling REST calls
 *
 * Introduce
 * 1. REST - Representational State Transfer - Architecture
 * - Client- server architecture
 * - statelesness
 * - cacheability
 * - a layerd system
 * - code on demand (optional)
 * - uniform interface
 * 2. API - Application Programming Interface (web services)
 * - HTTP - Hypertext Transfer protocol  - based on RESTful API
 *  data communication protocal for the www (Internet)
 *  - HTTP methods : GET, POST, PUT, DELETE ...
 *  - HTML documents, images, style sheets
 * 3. JSON - JavaScript Object Notation : text based data transfer format.
 * JSON converts - GSON, Jackson or Moshi
 * to convert json string to data objects
 * Moshi - is more lightweight
 *
 * Glide - to load data from the server ..
 * 4. XML - Extensible Markup Language : datastructure used by RESTful services
 *
 * Fetch Data from Network Endpoint using Retrofit
 * To present the dynamic content in our App.
 *
 * RESTful API : [https://api.thecatapi.com/v1/ ]
 *
 *
 *
 * ********* PARSING A JSON RESPONSE ***************
 *
 *
 *
 *
 * ****************** LOADING IMAGE FROM REMOTE URL USING GLIDE *************************
 *
 *
 * ****************** RECYCLER VIEW *****************************************************************
 * 1. handle onClick
 * 2. support different item view type
 * 3. Swipe to remove item
 * 4. Add item Interactively
 */
class CatApiActivity : AppCompatActivity() {

    private val serverTextView : TextView by lazy {
        findViewById(R.id.server_response)
    }

    private val catImageView : ImageView by lazy {
        findViewById(R.id.cat_iv)
    }

    private val addItemButton: View by lazy {
        findViewById(R.id.main_add_item_button)
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.thecatapi.com/v1/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    private val apiService by lazy { retrofit.create(CatApiService::class.java) }

    private val imageLoader : ImageLoader by lazy {
        GlideImageLoader(this)
    }

    private val recyclerView : RecyclerView by lazy {
        findViewById(R.id.cat_rv)
    }

    private val catAdapter by lazy { CatAdapter(layoutInflater, imageLoader,
        object: OnClickListener {
            override fun onItemClick(catUiModel: CatUiModel) {
                return showSelectionDialog(catUiModel)
            }
        }
    ) }

    private val listItemsAdapter by lazy {
        ListItemsAdapter(
            layoutInflater,
            GlideImageLoader(this),
            object: ListItemOnClickListener{
                override fun onItemClick(catUiModel: CatUiModel) {
                    showSelectionDialog(catUiModel)
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catapi)
        /*
        recyclerView.adapter = catAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        catAdapter.setData(
            listOf(
                CatUiModel(
                    Gender.Male,
                    CatBreed.BalineseJavanese,
                    "Fred",
                    "Silent and deadly",
                    "https://cdn2.thecatapi.com/images/DBmIBhhyv.jpg"
                ),
                CatUiModel(
                    Gender.Female,
                    CatBreed.ExoticShorthair,
                    "Wilma",
                    "Cuddly assassin",
                    "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg"
                ),
                CatUiModel(
                    Gender.Unknown,
                    CatBreed.AmericanCurl,
                    "Curious George",
                    "Award winning investigator",
                    "https://cdn2.thecatapi.com/images/vJB8rwfdX.jpg"
                )
            )
        )

         */

        recyclerView.adapter = listItemsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        // we attach ItemTouchHelper to our RecyclerView
        val itemTouchHelper = ItemTouchHelper(listItemsAdapter.swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        // add Items
        addItemButton.setOnClickListener {
            listItemsAdapter.addItem(
                1,
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Female,
                        CatBreed.BalineseJavanese,
                        "Anonymous",
                        "Unknown",
                        "https://cdn2.thecatapi.com/images/zJkeHza2K.jpg"
                    )
                )
            )
        }

        listItemsAdapter.setData(
            listOf(
                ListItemUiModel.Title("Sleeper Cat"),
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Male,
                        CatBreed.ExoticShorthair,
                        "Garvey",
                        "Garvey is as a lazy, fat, and cynical orange cat.",
                        "https://cdn2.thecatapi.com/images/FZpeiLi4n.jpg"
                    )
                ),
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Unknown,
                        CatBreed.AmericanCurl,
                        "Curious George",
                        "Award winning investigator",
                        "https://cdn2.thecatapi.com/images/vJB8rwfdX.jpg"
                    )
                ),
                ListItemUiModel.Title("Active Cat"),
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Male,
                        CatBreed.BalineseJavanese,
                        "Fred",
                        "Silent and deadly",
                        "https://cdn2.thecatapi.com/images/DBmIBhhyv.jpg"
                    )
                ),
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Female,
                        CatBreed.ExoticShorthair,
                        "Wilma",
                        "Cuddly assassin",
                        "https://cdn2.thecatapi.com/images/KJF8fB_20.jpg"
                    )
                ),
                ListItemUiModel.Cat(
                    CatUiModel(
                        Gender.Male,
                        CatBreed.ExoticShorthair,
                        "Tim",
                        "Tim, AKA Jasper, is very energetic, determined yet somewhat... Slow.",
                        "https://cdn2.thecatapi.com/images/y61B6bFCh.jpg"
                    )
                )
            )
        )
        // getCatResponse()
    }

    private fun showSelectionDialog(catUiModel: CatUiModel) {
        AlertDialog.Builder(this)
            .setTitle("Cat Selected")
            .setMessage("You have selected cat ${catUiModel.name}")
            .setPositiveButton("OK"){ _, _ ->
            }.show()

    }
    private fun getCatResponse(){
        val call = apiService.searchImages(1, "full")
        call.enqueue(object : Callback<List<ImageData>>{
            override fun onResponse(call: Call<List<ImageData>>, response: Response<List<ImageData>>) {
                if (response.isSuccessful){
                    // update the response in the textview
                    val imageUrl = response.body()?.firstOrNull()?.imageUrl.orEmpty()
                    serverTextView.text = imageUrl
                    if (imageUrl.isNotBlank()){
                        imageLoader.loadImage(imageUrl, catImageView)
                    } else {
                        Log.d(TAG, "Missing Image URL")
                    }
                } else {
                    Log.e(
                        TAG,
                        "Failed to get the results {${response.errorBody()?.string()?:" "}}"
                    )
                }
            }

            override fun onFailure(call: Call<List<ImageData>>, t: Throwable) {
               Log.e(TAG, "Failed to get search results: $t")
            }

        })
    }
    companion object {
        const val TAG = "CatApiActivity"
    }
}