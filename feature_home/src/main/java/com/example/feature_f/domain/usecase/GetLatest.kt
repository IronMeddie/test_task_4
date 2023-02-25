package com.example.feature_f.domain.usecase

import com.example.feature_f.domain.repository.RepositoryLatest
import javax.inject.Inject

class GetLatest @Inject constructor(private val repository : RepositoryLatest) {
    operator fun invoke() = repository.getLatest()
}