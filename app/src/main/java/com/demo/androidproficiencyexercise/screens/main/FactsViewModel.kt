package com.demo.androidproficiencyexercise.screens.main

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.demo.androidproficiencyexercise.api.ApiClient
import com.demo.androidproficiencyexercise.api.ApiFactInterface
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ashish Nair on 06/06/20.
 */
class FactsViewModel : ViewModel() {
    var isRefreshing = ObservableBoolean()

    private lateinit var subscription: Disposable
    private fun loadFacts() {
        ApiClient.getClient()?.create(ApiFactInterface::class.java)
            ?.getFacts()?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())?.subscribe({ facts ->
            }, {

            }
            )
    }

    fun onRefresh() {
        loadFacts()
    }
}