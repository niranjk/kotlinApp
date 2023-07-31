package com.niranjankhatri.kotlinapp.fragments.dynamic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.fragments.dualpane.DetailFragment
import com.niranjankhatri.kotlinapp.fragments.dualpane.ListFragment
import com.niranjankhatri.kotlinapp.fragments.dualpane.StarSignClickListener


class DynamicDemoActivity : AppCompatActivity(), StarSignClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_demo)
        if (savedInstanceState == null){
            findViewById<FragmentContainerView>(R.id.fragment_container)?.let { containerView ->
                val listFragment = ListFragment()
                supportFragmentManager.beginTransaction()
                    .add(containerView.id, listFragment).commit()
            }
        }
    }

    override fun onClicked(id: Int) {
       findViewById<FragmentContainerView>(R.id.fragment_container)?.let { containerView ->
           val detailFragment = DetailFragment.newInstance(id)
           supportFragmentManager.beginTransaction()
               .replace(containerView.id, detailFragment)
               .addToBackStack(null) // note : not to make your app exit on back button
               .commit()
       }
    }
}