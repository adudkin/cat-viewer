package com.example.catviewer.insertion

import retrofit2.Call
import retrofit2.http.GET

interface CatService {
    @GET("/v1/images/search")
    fun getAll(): Call<List<CatResponse>>
}