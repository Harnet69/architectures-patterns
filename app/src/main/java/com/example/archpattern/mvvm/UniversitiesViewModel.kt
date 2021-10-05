package com.example.archpattern.mvvm

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.archpattern.model.UniversitiesService
import com.example.archpattern.model.University
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*

class UniversitiesViewModel : ViewModel() {
    //TODO inject by Hilt it instead of instantiate
    private val universitiesService = UniversitiesService()
    private val job: Job? = null
    private val exceptionHandler = CoroutineExceptionHandler{coroutineContext, throwable ->
        CoroutineScope(Dispatchers.Main).launch {
        universitiesError.value = throwable.localizedMessage
        }
    }

    val universitiesList: MutableLiveData<List<String>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val universitiesError: MutableLiveData<String?> = MutableLiveData()

    init {
//        fetchCountriesWithRx()
        fetchCountriesWithCoroutines()
    }

    @SuppressLint("CheckResult")
    fun fetchCountriesWithRx() {
        isLoading.value = true
        universitiesService.getRemoteUniversities()
            // do operation on background thread
            .subscribeOn(Schedulers.newThread())
            // observe changes on the main thread
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<University>>() {
                @SuppressLint("CheckResult")
                override fun onSuccess(universities: List<University>) {
                    if (universities.isNotEmpty()) {
                        universitiesList.value = universities.map { university -> university.name }
                        universitiesError.value = null
                        isLoading.value = false
                    }
                }

                override fun onError(e: Throwable) {
                    isLoading.value = false
                    universitiesError.value = e.localizedMessage
                }
            })
    }

    //get with coroutines
    private fun fetchCountriesWithCoroutines() {
        isLoading.value = true
        CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = universitiesService.getRemoteUniversitiesCoroutines()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    universitiesList.value = response.body()?.map { university -> university.name }
                    universitiesError.value = null
                } else {
                    universitiesError.value = response.message()
                }
            }
        }
    }
}