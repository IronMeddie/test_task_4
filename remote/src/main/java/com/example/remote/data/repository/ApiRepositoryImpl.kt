package com.example.remote.data.repository

import android.util.Log
import com.example.models.FlashSale
import com.example.models.Latest
import com.example.remote.data.remote.ApiService
import com.example.remote.data.remote.DataResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(private val api: ApiService): BaseRepository,
    RepositoryLatest {
    override fun getLatest(): Flow<DataResource<List<Latest>>> = safeApiCall {
        val list = api.getLatest().latests
        Log.d("chekCode", list.size.toString())
        list
         }
    override fun getSale(): Flow<DataResource<List<FlashSale>>> = safeApiCall { api.getSale().sales }
}