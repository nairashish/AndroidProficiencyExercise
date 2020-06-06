package com.demo.androidproficiencyexercise.screens.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.demo.androidproficiencyexercise.R
import com.demo.androidproficiencyexercise.databinding.ActivityFactsBinding

class FactsActivity : AppCompatActivity() {

    private lateinit var factsViewModel: FactsViewModel
    private lateinit var factsActivityBinding:ActivityFactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        factsActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_facts)

        factsViewModel = ViewModelProviders.of(this).get(FactsViewModel::class.java)
        factsActivityBinding.dataViewModel = factsViewModel
    }

    private fun setHeaderTitle(title: String) {
        supportActionBar?.title = title
    }
}
