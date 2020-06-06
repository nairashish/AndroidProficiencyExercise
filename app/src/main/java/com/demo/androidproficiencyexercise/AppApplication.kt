package com.demo.androidproficiencyexercise

import android.app.Application
import com.google.gson.Gson

/**
 * Created by Ashish Nair on 05/06/20.
 */
class AppApplication: Application() {
    lateinit var instance: AppApplication

    override fun onCreate() {
        super.onCreate()
        instance = this

    }
}