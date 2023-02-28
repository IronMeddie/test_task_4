package com.example.feature_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.GetDetails
import com.example.models.Details
import com.example.remote.data.remote.DataResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val getDetails : GetDetails) : ViewModel() {

    private val _details = MutableStateFlow<DataResource<Details>>(DataResource.Loading)
    val details = _details.asStateFlow()

    init {
        loadDetails()
    }

    fun loadDetails(){
        viewModelScope.launch {
            getDetails().collectLatest {
                _details.value = it
            }
        }
    }

}