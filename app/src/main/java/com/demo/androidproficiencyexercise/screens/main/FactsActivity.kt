package com.demo.androidproficiencyexercise.screens.main

import android.content.*
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.androidproficiencyexercise.R
import com.demo.androidproficiencyexercise.databinding.ActivityFactsBinding
import com.demo.androidproficiencyexercise.model.EmptyView
import com.demo.androidproficiencyexercise.utility.Global

class FactsActivity : AppCompatActivity() {

    private lateinit var factsViewModel: FactsViewModel
    private lateinit var factsActivityBinding: ActivityFactsBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        factsActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_facts)

        initViews()
        setViewModel()
        checkInternetConnectivity()


        factsActivityBinding.dataViewModel = factsViewModel
        factsActivityBinding.emptyModel =
            EmptyView(
                R.mipmap.ic_launcher_round, getString(R.string.no_record)

            )
    }

    private fun setViewModel() {
        factsViewModel = ViewModelProviders.of(this).get(FactsViewModel::class.java)
        factsViewModel.getHeaderTitle().observe(this, Observer {
            if (it != null)
                setHeaderTitle(it)
        })
    }

    private fun initViews() {
        factsActivityBinding.rvDataList.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        factsActivityBinding.rvDataList.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

    }

    private fun setHeaderTitle(title: String) {
        supportActionBar?.title = title
    }

    /**
     * Function to check internet availability
     */
    private fun checkInternetConnectivity() {
        factsViewModel.isNetworkAvailable.observe(this, Observer { isConnected ->
            if (!isConnected) {
                Global.noNetworkDialog(this, DialogInterface.OnClickListener { dialog, which ->
                    dialog.dismiss()
                })
            }
        })


    }

}

