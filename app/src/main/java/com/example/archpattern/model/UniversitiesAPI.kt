package com.example.archpattern.model

import io.reactivex.Single
import retrofit2.http.GET

//REST API  http://universities.hipolabs.com/search?country=United+States

interface UniversitiesAPI {
    @GET("search?country=United+States")
    fun getUniversities(): Single<List<University>>
}