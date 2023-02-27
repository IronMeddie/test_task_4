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
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentUserUC: GetCurrentUser,
    private val latestUC: GetLatest,
    private val saleUC: GetSale
) : ViewModel() {


    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()

    private val _latest = MutableStateFlow<DataResource<List<Latest>>>(DataResource.Loading)
    val latest = _latest.asStateFlow()

    private val _sale = MutableStateFlow<DataResource<List<FlashSale>>>(DataResource.Loading)
    val sale = _sale.asStateFlow()

    private val _user = MutableStateFlow<DataResource<User?>>(DataResource.Loading)
    val user = _user.asStateFlow()



    init {
        getCurrentUser()
        getLatest()
        getSale()
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            val resp = currentUserUC()
            _user.emit(DataResource.Success(resp))
            Log.d("checkCodeHomeViewModel", resp?.firstName ?: "user is null" )
        }
    }

    fun getLatest() {
        latestUC().onEach {
            _latest.value = it
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

sealed class AuthState(){
   object Loading : AuthState()
   object Authorizated : AuthState()
   object NotAuthorizated : AuthState()
}

