package com.ani.android.life.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.ani.android.life.ui.databinding.FragmentBlankBinding
import kotlinx.coroutines.launch


class BlankFragment : Fragment() {

    private val viewModel: BlankViewModel = BlankViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: FragmentBlankBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_blank, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.vm = viewModel

//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.blankState.collect {
//                binding.invalidateAll()
//            }
//        }

        return binding.root
    }

}