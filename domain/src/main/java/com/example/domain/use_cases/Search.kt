package com.example.domain.use_cases

import android.util.Log
import com.example.remote.data.remote.DataResource
import com.example.remote.data.repository.ApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Search @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke(string: String): Flow<List<String>> {
        return repository.search(string).map {
            if (it is DataResource.Success) {
                it.data.filter { it.contains(string, true) }
            } else {
                if (it is DataResource.Failure) Log.d("checkCodeSearchUC", it.errorBody)
                emptyList()
            }
        }
    }
}