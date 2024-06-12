package com.ani.android.life.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.ani.android.life.ui.databinding.FragmentCallsBinding
import com.ani.android.life.ui.databinding.FragmentChatBinding

class CallsFragment: Fragment() {

    private val viewModel:CallsViewModel = CallsViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentCallsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_calls, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

        return binding.root
    }
}