package com.orbitalsonic.backstackfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.orbitalsonic.backstackfragment.databinding.FragmentHomeBinding
import com.orbitalsonic.backstackfragment.databinding.FragmentSampleBinding

class SampleFragment :  Fragment()  {

    private lateinit var binding:FragmentSampleBinding
    private var callback: OnBackPressedCallback? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sample, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sonicBackPress()

    }

    private fun sonicBackPress() {
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onBack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback!!)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBack()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun onBack(){
        findNavController().popBackStack()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        callback?.remove()
    }

}