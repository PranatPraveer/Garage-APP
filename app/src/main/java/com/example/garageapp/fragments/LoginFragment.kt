package com.example.garageapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.garageapp.R
import com.example.garageapp.Utils.FirebaseResult
import com.example.garageapp.ViewModel.AuthViewModel
import com.example.garageapp.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding?=null
    private val binding get() = _binding!!

    private val AuthViewModel by viewModels<AuthViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentLoginBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.BtnLogin.setOnClickListener {
            val email= binding.EtEmailaddress.text.toString()
            val password= binding.EtPassword.text.toString()
            AuthViewModel.signIn(email,password)
            AuthViewModel._UserSignedIn.observe(viewLifecycleOwner, Observer {
                when(it){
                    is FirebaseResult.Error -> Toast.makeText(context,it.message, Toast.LENGTH_SHORT).show()
                    is FirebaseResult.Loading -> Toast.makeText(context,"Loading",Toast.LENGTH_SHORT).show()
                    is FirebaseResult.Success -> findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                }
            })
        }
    }
}