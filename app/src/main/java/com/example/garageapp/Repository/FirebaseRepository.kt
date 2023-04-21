package com.example.garageapp.Repository


import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.garageapp.Utils.FirebaseResult
import com.google.firebase.auth.FirebaseAuth

class FirebaseRepository {
    lateinit var firebaseAuth: FirebaseAuth
    private val _UserSignedUp= MutableLiveData<FirebaseResult<String>>()
    val UserSignedUp:MutableLiveData<FirebaseResult<String>> = _UserSignedUp

    private val _UserSignedIn= MutableLiveData<FirebaseResult<String>>()
    val UserSignedIn:MutableLiveData<FirebaseResult<String>> = _UserSignedIn

    suspend fun signInUser(Email: String,Password: String){
        firebaseAuth= FirebaseAuth.getInstance()
        val email= Email
        val password= Password
        if (email.isBlank()||password.isBlank()){
            UserSignedUp.postValue(FirebaseResult.Error("Please fill all the fields"))
        }
        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener{
            if (it.isSuccessful){
                UserSignedIn.postValue(FirebaseResult.Success("Sucessfully Signed In"))
            }
            else{
                UserSignedIn.postValue(FirebaseResult.Error("Error in Logging In"))
            }
        }
    }

    suspend fun signupUser(Email: String, Password: String, ConfirmPassword: String) {
        firebaseAuth= FirebaseAuth.getInstance()
        UserSignedUp.postValue(FirebaseResult.Loading())
        val email = Email
        val password = Password
        val confirmpassword = ConfirmPassword
        if (email.isBlank() || password.isBlank() || confirmpassword.isBlank()) {
            UserSignedUp.postValue(FirebaseResult.Error("Please fill all the fields"))
        }
        if (password != confirmpassword) {
            UserSignedUp.postValue(FirebaseResult.Error("Password not match"))
        }
        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                UserSignedUp.postValue(FirebaseResult.Success("Sucessfully Signed Up"))
            } else {
                UserSignedUp.postValue(FirebaseResult.Error("Error creating the user"))
                Log.d("pp",it.result.toString())
            }
        }
    }
}