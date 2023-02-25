package com.example.remote.data.remote

import com.example.models.Details
import com.example.models.FlashSale
import com.example.models.Latest
import retrofit2.http.GET


interface ApiService {

    @GET("/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest(): List<Latest>

    @GET("/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getSale(): List<FlashSale>


    @GET("/f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getDetails(): List<Details>


}