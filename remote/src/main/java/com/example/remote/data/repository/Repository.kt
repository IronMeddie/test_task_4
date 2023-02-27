package com.example.remote.data.repository

import com.example.models.FlashSale
import com.example.models.Latest
import com.example.remote.data.remote.DataResource
import kotlinx.coroutines.flow.Flow

interface RepositoryLatest  {
    fun getLatest() : Flow<DataResource<List<Latest>>>
    fun getSale() : Flow<DataResource<List<FlashSale>>>
}