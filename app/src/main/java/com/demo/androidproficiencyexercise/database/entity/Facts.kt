package com.demo.androidproficiencyexercise.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "facts")
data class Facts(
    @PrimaryKey(autoGenerate = true)
    var id: Int?,
    val imageHref: String? = null,
    val description: String? = null,
    val title: String? = null
) : Serializable
