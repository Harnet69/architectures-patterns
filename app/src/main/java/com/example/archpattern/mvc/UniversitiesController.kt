package com.example.archpattern.mvc

import android.annotation.SuppressLint
import android.util.Log
import com.example.archpattern.model.UniversitiesService
import com.example.archpattern.model.University
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class UniversitiesController(val view: MVCActivity) {
    //TODO inject by Hilt it instead of instantiate
    private val universitiesService = UniversitiesService()

    init {
        view.onLoading()
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
                    view.setValues(t.map { it.name })
                }

                override fun onError(e: Throwable) {
                    view.onError(e.localizedMessage)
                }
            })
    }
}