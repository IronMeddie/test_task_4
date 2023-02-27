package com.example.feature_f.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.database.entity.User
import com.example.feature_f.domain.usecase.GetCurrentUser
import com.example.feature_f.domain.usecase.GetLatest
import com.example.feature_f.domain.usecase.GetSale
import com.example.models.FlashSale
import com.example.models.Latest
import com.example.remote.data.remote.DataResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentUserUC: GetCurrentUser,
    private val latestUC: GetLatest,
    private val saleUC: GetSale
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()

    private val _latest = MutableStateFlow<DataResource<List<Latest>>>(DataResource.Loading)
    val latest = _latest.asStateFlow()

    private val _sale = MutableStateFlow<DataResource<List<FlashSale>>>(DataResource.Loading)
    val sale = _sale.asStateFlow()

    init {
        getCurrentUser()
        getLatest()
        getSale()
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            _user.value = currentUserUC()
        }
    }

    fun getLatest() {
        latestUC().onEach {
            _latest.value = it
            when(it){
                is DataResource.Loading ->{}
                is DataResource.Success -> Log.d("checkCodeVM", " datasource Success")
                is DataResource.Failure -> Log.d("checkCodeVM", " datasource FAIL, ${it.errorCode}  ${it.errorBody}")

            }

        }.launchIn(viewModelScope)
    }

    fun getSale() {
        saleUC().onEach {
            _sale.value = it
        }.launchIn(viewModelScope)
    }

    fun updateSearch(str: String) {
        if (!str.contains("\n")) _search.value = str
    }

}