package com.example.feature_f.data

import com.example.feature_f.domain.repository.RepositoryLatest
import com.example.models.FlashSale
import com.example.models.Latest
import com.example.remote.data.remote.ApiService
import com.example.remote.data.remote.DataResource
import com.example.remote.data.repository.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryPage1 @Inject constructor(private val api: ApiService): BaseRepository, RepositoryLatest{
    override fun getLatest(): Flow<DataResource<List<Latest>>> = safeApiCall { api.getLatest() }
    override fun getSale(): Flow<DataResource<List<FlashSale>>> = safeApiCall { api.getSale() }
}