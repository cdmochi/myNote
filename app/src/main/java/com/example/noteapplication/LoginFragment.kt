package com.example.noteapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.noteapplication.R
import com.example.noteapplication.databinding.LoginFragmentBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
       inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<LoginFragmentBinding>(inflater, R.layout.login_fragment,container,false)


        binding.loginButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_noteListFragment)
        }


        return binding.root

    }
}