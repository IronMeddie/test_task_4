package com.example.domain.use_cases

import com.example.remote.data.repository.ApiRepository
import javax.inject.Inject

class GetSale @Inject constructor(private val repository: ApiRepository) {
    operator fun invoke() = repository.getSale()
}