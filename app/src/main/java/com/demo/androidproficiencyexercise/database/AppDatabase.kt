package com.demo.androidproficiencyexercise.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.demo.androidproficiencyexercise.R
import com.demo.androidproficiencyexercise.database.dao.FactsDao
import com.demo.androidproficiencyexercise.database.entity.Facts
import com.demo.androidproficiencyexercise.database.entity.FactsTitle

/**
 * Created by Ashish Nair on 05/06/20.
 */

@Database(entities = [Facts::class, FactsTitle::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun factsDao(): FactsDao

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