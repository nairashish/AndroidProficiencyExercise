package com.demo.androidproficiencyexercise.screens.main

import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.demo.androidproficiencyexercise.AppApplication
import com.demo.androidproficiencyexercise.api.ApiClient
import com.demo.androidproficiencyexercise.api.ApiFactInterface
import com.demo.androidproficiencyexercise.database.entity.Facts
import com.demo.androidproficiencyexercise.database.entity.FactsTitle
import com.demo.androidproficiencyexercise.utility.Global
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by Ashish Nair on 06/06/20.
 */
class FactsViewModel : ViewModel() {

    val factsListAdapter: FactsListAdapter = FactsListAdapter()
    var isRefreshing = ObservableBoolean()

    var isNetworkAvailable: MutableLiveData<Boolean> = MutableLiveData()
    val emptyVisibility: MutableLiveData<Int> = MutableLiveData()
    val factsTitle: MutableLiveData<String> = MutableLiveData()

    private lateinit var subscription: Disposable

    init {
        loadFacts(false)
        factsListAdapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                super.onChanged()
                if (factsListAdapter.itemCount > 0)
                    emptyVisibility.value = View.GONE
                else
                    emptyVisibility.value = View.VISIBLE
            }
        })
    }

    private fun loadFacts(isRefresh: Boolean) {

        subscription = Observable.fromCallable {
            if (isRefresh) {
                AppApplication.appDatabase.factsDao().deleteTableFacts()
                AppApplication.appDatabase.factsDao().deleteTableFactsTitle()
            }
            AppApplication.appDatabase.factsDao().getFacts()
        }
            .concatMap { factsList ->
                if (factsList.isEmpty()) {
                    isNetworkAvailable.postValue(Global.isInternetAvailable())
                    if (Global.isInternetAvailable()) {
                        ApiClient.getClient()?.create(ApiFactInterface::class.java)
                            ?.getFacts()?.concatMap { facts ->
                                facts.rows?.let {
                                    facts.rows = it.filter { fact ->
                                        (fact.title != null && fact.description != null)
                                    }
                                    AppApplication.appDatabase.factsDao().insertFacts(
                                        facts.rows!!
                                    )
                                }
                                facts.title?.let {
                                    FactsTitle(
                                        it
                                    )
                                }?.let { AppApplication.appDatabase.factsDao().insertFactTitle(it) }
                                Observable.just(facts.rows)
                            }
                    } else {
                        Observable.error(Throwable("Not connected to Internet"))
                    }
                } else {
                    Observable.just(factsList)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                { result ->
                    onRetrievePostListSuccess(result as List<Facts>)
                },
                { onRetrievePostListError() }
            )


    }

    fun onRefresh() {
        loadFacts(true)
    }


    private fun onRetrievePostListStart() {
        isRefreshing.set(true)
    }

    private fun onRetrievePostListFinish() {
    }

    private fun onRetrievePostListSuccess(facts: List<Facts>) {
        isRefreshing.set(false)
        factsListAdapter.updateFactsList(facts)
    }

    private fun onRetrievePostListError() {
        isRefreshing.set(false)
    }

    override fun onCleared() {
        super.onCleared()
        if (subscription != null)
            subscription.dispose()
    }

    fun getHeaderTitle(): LiveData<String> {
        return AppApplication.appDatabase.factsDao().getFactsTitle()
    }
}