package com.niranjankhatri.kotlinapp.fragments.dualpane

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.kotlinapp.R

const val STAR_SIGN_ID = "STAR_SIGN_ID"

interface StarSignClickListener{
    fun onClicked(id: Int)
}

class DualpaneDemoActivity : AppCompatActivity(), StarSignClickListener {

    var isDualPaneLayout: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dualpane)
        isDualPaneLayout = findViewById<View>(R.id.star_sign_detail) != null
    }

    override fun onClicked(id: Int) {
        // here we will have the id of the selected Star Sign
        if (isDualPaneLayout){
           val detailFragment = supportFragmentManager.findFragmentById(R.id.star_sign_detail)  as DetailFragment
            detailFragment.setStarSignData(id)
        } else {
            val detailIntent = Intent(this, DetailActivity::class.java).apply {
                putExtra(STAR_SIGN_ID, id)
            }
            startActivity(detailIntent)
        }
    }
}