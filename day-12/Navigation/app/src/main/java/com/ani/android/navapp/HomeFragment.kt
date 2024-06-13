package com.ani.android.navapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController


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

        HomeFragmentDirections.actionHomeToUsersFragment()


        view.findViewById<ImageView>(R.id.imageView).setOnClickListener {
//            findNavController().navigate(R.id.action_home_to_usersFragment)

            val action = HomeFragmentDirections.actionHomeToUsersFragment()
            findNavController().navigate(action)
        }

        view.findViewById<ImageView>(R.id.imageView2).setOnClickListener {
//            findNavController().navigate(R.id.action_homeFragment_to_invoicesFragment2)

            val invId = 100
            val action = HomeFragmentDirections.actionHomeFragmentToInvoicesFragment2(invId)

            findNavController().navigate(action)
       }

        view.findViewById<ImageView>(R.id.imageView3).setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_billsFragment)
            val action = HomeFragmentDirections.actionHomeFragmentToBillsFragment()
            findNavController().navigate(action)
        }

        view.findViewById<ImageView>(R.id.imageView4).setOnClickListener {
//            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_settingsFragment)
            val action = HomeFragmentDirections.actionHomeFragmentToSettingsFragment()
            findNavController().navigate(action)
        }

    }
}