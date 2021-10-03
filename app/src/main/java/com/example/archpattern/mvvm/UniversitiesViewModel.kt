package com.example.archpattern.mvvm

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.archpattern.model.UniversitiesService
import com.example.archpattern.model.University
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class UniversitiesViewModel: ViewModel() {
    //TODO inject by Hilt it instead of instantiate
    private val universitiesService = UniversitiesService()

    val universitiesList: MutableLiveData<List<String>> = MutableLiveData()
    val universitiesError: MutableLiveData<String?> = MutableLiveData()

    init {
        fetchCountries()
    }

    @SuppressLint("CheckResult")
    fun fetchCountries() {
        universitiesService.getRemoteUniversities()
            // do operation on background thread
            .subscribeOn(Schedulers.newThread())
            // observe changes on the main thread
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<University>>() {
                @SuppressLint("CheckResult")
                override fun onSuccess(universities: List<University>) {
                    if(universities.isNotEmpty()) {
                        universitiesList.value = universities.map { university -> university.name }
                        universitiesError.value = null
                    }
                }

                override fun onError(e: Throwable) {
                    universitiesError.value = e.localizedMessage
                }
            })
    }
}