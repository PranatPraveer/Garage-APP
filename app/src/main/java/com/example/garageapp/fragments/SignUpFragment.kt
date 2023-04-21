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
import com.example.garageapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {
    private var _binding: FragmentSignUpBinding?=null
    private val binding get() = _binding!!

    private lateinit var auth:FirebaseAuth
    private val AuthViewModel by viewModels<AuthViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentSignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth=FirebaseAuth.getInstance()
        if(auth.currentUser!=null){
            findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
        }
        binding.BtnSignin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }
        binding.BtnSignup.setOnClickListener {
            val email=binding.EtEmailaddress.text.toString()
            val password=binding.EtPassword.text.toString()
            val confirmpassword=binding.Etconfirmpassword.text.toString()
            AuthViewModel.signUp(email,password,confirmpassword)
            AuthViewModel._UserSignedUp.observe(viewLifecycleOwner, Observer {
                when(it){
                    is FirebaseResult.Error -> Toast.makeText(context,it.message,Toast.LENGTH_SHORT).show()
                    is FirebaseResult.Loading -> Toast.makeText(context,"Loading",Toast.LENGTH_SHORT).show()
                    is FirebaseResult.Success -> findNavController().navigate(R.id.action_signUpFragment_to_mainFragment)
                }
            })
        }
    }
}