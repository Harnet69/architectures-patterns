package com.example.archpattern.model

import com.google.gson.annotations.SerializedName

//REST API
// http://universities.hipolabs.com/search?country=United+States

data class University(
    @SerializedName("name")
    val name: String,
    //TODO use this fields only when recyclerView will be implemented
//    @SerializedName("country")
//    val country: String,
//    @SerializedName("alpha_two_code")
//    val alphaTwoCode: String,
//    @SerializedName("web_pages")
//    val webPages: Array<String>
)
