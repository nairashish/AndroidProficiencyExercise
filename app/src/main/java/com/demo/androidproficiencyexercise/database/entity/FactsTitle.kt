package com.demo.androidproficiencyexercise.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "facts_title")
data class FactsTitle(
    @PrimaryKey
    val title: String = ""
) : Serializable
