package com.example.peter.kpropertiesmvvm.repository.remote

import com.example.peter.kpropertiesmvvm.repository.model.Model
import retrofit2.Call
import retrofit2.http.GET

interface PropertyApi {

    @GET("properties")
    fun getProperties(): Call<Model.Result>

}