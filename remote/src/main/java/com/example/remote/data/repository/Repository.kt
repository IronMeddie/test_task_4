package com.example.remote.data.repository

import com.example.models.Details
import com.example.models.FlashSale
import com.example.models.Latest
import com.example.remote.data.remote.DataResource
import kotlinx.coroutines.flow.Flow

interface ApiRepository  {
    fun getLatest() : Flow<DataResource<List<Latest>>>
    fun getSale() : Flow<DataResource<List<FlashSale>>>
    fun getDetails() : Flow<DataResource<Details>>
    fun search(string: String) : Flow<DataResource<List<String>>>
}