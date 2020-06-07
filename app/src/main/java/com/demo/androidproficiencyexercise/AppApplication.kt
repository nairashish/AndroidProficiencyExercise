package com.demo.androidproficiencyexercise

import android.app.Application
import com.demo.androidproficiencyexercise.database.AppDatabase

/**
 * Created by Ashish Nair on 05/06/20.
 */
class AppApplication : Application() {

    companion object {

        lateinit var instance: AppApplication
        lateinit var appDatabase: AppDatabase
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appDatabase = AppDatabase.getAppDataBase(applicationContext)!!

    }
}