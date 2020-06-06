package com.demo.androidproficiencyexercise.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.androidproficiencyexercise.R

/**
 * Created by Ashish Nair on 05/06/20.
 */
abstract class AppDatabase : RoomDatabase() {

    companion object {

        var appDatabase: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            val db_name =
                context.getString(R.string.app_name).toLowerCase() + "_db"
            if (appDatabase == null) {
                synchronized(AppDatabase::class) {
                    appDatabase =
                        Room.databaseBuilder(
                            context.applicationContext,
                            AppDatabase::class.java,
                            db_name
                        ).fallbackToDestructiveMigration().build()
                }
            }
            return appDatabase
        }
    }
}