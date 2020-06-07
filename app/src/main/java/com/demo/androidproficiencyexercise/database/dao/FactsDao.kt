package com.demo.androidproficiencyexercise.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.demo.androidproficiencyexercise.database.entity.Facts
import com.demo.androidproficiencyexercise.database.entity.FactsTitle

/**
 * Created by Ashish Nair on 07/06/20.
 */
@Dao
interface FactsDao {
    @Query("SELECT * FROM facts")
    fun getFacts(): List<Facts>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFacts(facts: List<Facts>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFactTitle(factTitle: FactsTitle)

    @Query("SELECT title FROM facts_title")
    fun getFactsTitle(): LiveData<String>

    @Query("DELETE FROM facts")
    fun deleteTableFacts()

    @Query("DELETE FROM facts_title")
    fun deleteTableFactsTitle()
}