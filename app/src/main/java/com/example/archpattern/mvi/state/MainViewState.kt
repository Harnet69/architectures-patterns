package com.example.archpattern.mvi.state

import com.example.archpattern.model.University

data class MainViewState(
    var universitiesList: ArrayList<University>? = null
)
