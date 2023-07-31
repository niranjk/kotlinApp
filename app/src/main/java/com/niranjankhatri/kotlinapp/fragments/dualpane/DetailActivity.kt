package com.niranjankhatri.kotlinapp.fragments.dualpane

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.niranjankhatri.kotlinapp.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        var starSignId = intent.extras?.getInt(STAR_SIGN_ID, 0) ?: 0
        val detailFragment = supportFragmentManager.findFragmentById(R.id.star_sign_detail) as DetailFragment
        detailFragment.setStarSignData(starSignId)
    }
}