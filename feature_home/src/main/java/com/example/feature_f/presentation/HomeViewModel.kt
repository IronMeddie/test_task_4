package com.example.feature_f.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.use_cases.GetCurrentUser
import com.example.domain.use_cases.GetLatest
import com.example.domain.use_cases.GetSale
import com.example.domain.use_cases.Search
import com.example.feature_f.R
import com.example.models.FlashSale
import com.example.models.Latest
import com.example.models.User
import com.example.remote.data.remote.DataResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val currentUserUC: GetCurrentUser,
    private val latestUC: GetLatest,
    private val saleUC: GetSale,
    private val searchUC: Search
) : ViewModel() {


    private val _search = MutableStateFlow("")
    val search = _search.asStateFlow()

    private val _user = MutableStateFlow<DataResource<User?>>(DataResource.Loading)
    val user = _user.asStateFlow()

    private val _state = MutableStateFlow<DataResource<HomeScreenState>>(DataResource.Loading)
    val state = _state.asStateFlow()

    private val _searchWords = MutableStateFlow<List<String>>(emptyList())
    val searchWords = _searchWords.asStateFlow()

    init {
        getCurrentUser()
        getHomeScreen()
    }

    fun getCurrentUser() {
        viewModelScope.launch {
            currentUserUC().collectLatest {
                _user.emit(DataResource.Success(it))
            }
        }
    }

    fun getHomeScreen() {
        viewModelScope.launch {
            latestUC().combine(saleUC()) { latest, sale ->
                when (latest) {
                    is DataResource.Success -> {
                        when (sale) {
                            is DataResource.Success -> _state.value = DataResource.Success(
                                HomeScreenState(
                                    latest = latest.data,
                                    sale = sale.data,
                                    brands = getBrands()
                                )
                            )
                            is DataResource.Failure -> _state.value = sale
                            is DataResource.Loading -> Unit
                        }
                    }
                    is DataResource.Failure -> _state.value = latest
                    is DataResource.Loading -> Unit
                }
            }.collect()
        }
    }


    private var job: Job? = null
    fun updateSearch(str: String) {
        if (!str.contains("\n")) _search.value = str
        job?.cancel()
        job = viewModelScope.launch {
            _searchWords.value = emptyList()
            if (str.isNotEmpty()) {
                delay(1000)
                searchUC(str).collect {
                    _searchWords.value = it
                }
            }
        }
    }

}

data class HomeScreenState(
    val sale: List<FlashSale> = emptyList(),
    val latest: List<Latest> = emptyList(),
    val brands: List<Brand> = emptyList(),
)

private fun getBrands() = listOf<Brand>(
    Brand("Reebok", R.drawable.brand_1),
    Brand("BMW", R.drawable.brand_2),
    Brand("Samsung", R.drawable.brand_3),
    Brand("Apple", R.drawable.brand_3),
)

data class Brand(val title: String, val imageUrl: Int)



