package com.example.remote.data.remote

import com.example.models.*
import com.example.remote.data.dto.ListLatest
import com.example.remote.data.dto.ListSale
import retrofit2.http.GET


interface ApiService {


    @GET("/v3/cc0071a1-f06e-48fa-9e90-b1c2a61eaca7")
    suspend fun getLatest(): ListLatest

    @GET("/v3/a9ceeb6e-416d-4352-bde6-2203416576ac")
    suspend fun getSale(): ListSale


    @GET("/v3/f7f99d04-4971-45d5-92e0-70333383c239")
    suspend fun getDetails(): List<Details>


}

