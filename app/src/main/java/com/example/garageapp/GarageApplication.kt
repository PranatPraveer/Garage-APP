package com.example.garageapp

import android.app.Application
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GarageApplication: Application() {
    override fun onCreate() {
        super.onCreate()
    }
}