package com.ani.android.navapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation


class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<ImageView>(R.id.imageView).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_home_to_usersFragment)
        }

        view.findViewById<ImageView>(R.id.imageView2).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_invoicesFragment2)
       }

        view.findViewById<ImageView>(R.id.imageView3).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_billsFragment)
        }

        view.findViewById<ImageView>(R.id.imageView4).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_settingsFragment)
        }

    }
}