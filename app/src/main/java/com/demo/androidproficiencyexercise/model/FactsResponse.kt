package com.demo.androidproficiencyexercise.model

import com.demo.androidproficiencyexercise.database.entity.Facts
import java.io.Serializable

data class FactsResponse(
    val title: String? = null,
    var rows: List<Facts>? = null
) : Serializable
