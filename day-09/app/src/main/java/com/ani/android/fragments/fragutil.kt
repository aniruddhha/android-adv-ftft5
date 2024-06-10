package com.ani.android.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

fun loadFragment(manager: FragmentManager,id: Int, frag: Fragment) {
    manager.apply {
        beginTransaction().apply {
            replace(id, frag)
            commit()
        }
    }
}