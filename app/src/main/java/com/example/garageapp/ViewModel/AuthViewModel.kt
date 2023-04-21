package com.example.garageapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.garageapp.Repository.FirebaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.viewModelScope
import com.example.garageapp.Utils.FirebaseResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


@HiltViewModel
class AuthViewModel @Inject constructor(private val firebaseRepository: FirebaseRepository):ViewModel() {

    val _UserSignedUp: LiveData<FirebaseResult<String>>
        get() = firebaseRepository.UserSignedUp

    val _UserSignedIn: LiveData<FirebaseResult<String>>
        get() = firebaseRepository.UserSignedIn

    fun signUp(Email: String, Password: String, ConfirmPassword: String){
        viewModelScope.launch {
            firebaseRepository.signupUser(Email, Password, ConfirmPassword)
        }
    }

    fun signIn(Email: String, Password: String){
        viewModelScope.launch {
            firebaseRepository.signInUser(Email, Password)
        }
    }
}