package com.example.remote.data.repository

import android.util.Log
import com.example.models.Details
import com.example.models.FlashSale
import com.example.models.Latest
import com.example.remote.data.remote.ApiService
import com.example.remote.data.remote.DataResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

internal class ApiRepositoryImpl @Inject constructor(private val api: ApiService): BaseRepository,
    ApiRepository {
    override fun getLatest(): Flow<DataResource<List<Latest>>> = safeApiCall { api.getLatest().latests }
    override fun getSale(): Flow<DataResource<List<FlashSale>>> = safeApiCall { api.getSale().sales }
    override fun getDetails(): Flow<DataResource<Details>> = safeApiCall { api.getDetails() }
    override fun search(string: String): Flow<DataResource<List<String>>> = safeApiCall { api.search().words }
}