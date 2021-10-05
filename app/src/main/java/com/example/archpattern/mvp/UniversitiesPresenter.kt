package com.example.archpattern.mvp

import android.annotation.SuppressLint
import com.example.archpattern.model.UniversitiesService
import com.example.archpattern.model.University
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UniversitiesPresenter(val universitiesView: UniversitiesView) {
    //TODO inject by Hilt it instead of instantiate
    private val universitiesService = UniversitiesService()
    //Interface for interacting with any view

    init {
        fetchCountriesWithRx()
    }

    @SuppressLint("CheckResult")
    fun fetchCountriesWithRx() {
        universitiesService.getRemoteUniversities()
            // do operation on background thread
            .subscribeOn(Schedulers.newThread())
            // observe changes on the main thread
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<University>>() {
                @SuppressLint("CheckResult")
                override fun onSuccess(t: List<University>) {
                    universitiesView.setValues(t.map { it.name })
                }

                override fun onError(e: Throwable) {
                    universitiesView.onError(e.localizedMessage)
                }
            })
    }

    // Separation between view and business logic
    interface UniversitiesView {
        fun setValues(universities: List<String>)
        fun onError(errorMsg: String?)
    }
}