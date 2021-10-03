package com.example.archpattern.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class UniversitiesService {
    private val BASE_URL = "http://universities.hipolabs.com/"

    private val universitiesAPI = Retrofit.Builder()
        .baseUrl(BASE_URL)
        // handle all basic communication, separate threads, errors and converts JSON to object of our class
        .addConverterFactory(GsonConverterFactory.create())
        // convert this object to observable Single<List<Universities>>
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(UniversitiesAPI::class.java)

    //get observable List from API
    fun getRemoteUniversities(): Single<List<University>> = universitiesAPI.getUniversities()

    //TODO create getLocalUniversities() function after creating repository to manage a data receiving
}