package com.niranjankhatri.kotlinapp.fragments.navgraph

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.niranjankhatri.kotlinapp.R
import com.niranjankhatri.kotlinapp.fragments.dualpane.STAR_SIGN_ID

/**
 * A simple [Fragment] subclass.
 * Use the [NavigationListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class NavigationListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // update the view of fragment or implement functionality here
        var starSigns = listOf<View>(
            view.findViewById(R.id.aquarius),
            view.findViewById(R.id.pisces),
            view.findViewById(R.id.aries),
            view.findViewById(R.id.taurus),
            view.findViewById(R.id.gemini),
            view.findViewById(R.id.cancer),
            view.findViewById(R.id.leo),
            view.findViewById(R.id.virgo),
            view.findViewById(R.id.libra),
            view.findViewById(R.id.scorpio),
            view.findViewById(R.id.sagittarius),
            view.findViewById(R.id.capricorn)
        )
        starSigns.forEach { sign ->
            val fragmentBundle = Bundle()
            fragmentBundle.putInt(STAR_SIGN_ID, sign.id)
           sign.setOnClickListener{
               Log.d("Navigation", sign.id.toString())
               Navigation.findNavController(it).navigate(
                   R.id.detailFragment,
                   fragmentBundle
               )
           }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            NavigationListFragment()
    }
}