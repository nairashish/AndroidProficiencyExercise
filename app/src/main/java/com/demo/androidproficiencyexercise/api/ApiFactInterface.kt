package com.demo.androidproficiencyexercise.api

import com.demo.androidproficiencyexercise.model.FactsResponse
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * Created by Ashish Nair on 06/06/20.
 */
interface ApiFactInterface {

    @GET("facts.json")
    fun getFacts(): Observable<FactsResponse>

}