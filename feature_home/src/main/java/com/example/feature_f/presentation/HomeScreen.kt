package com.example.feature_f.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.feature_f.R
import com.example.feature_f.presentation.components.*
import com.example.remote.data.remote.DataResource
import com.example.theme.SearchField


@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val search = viewModel.search.collectAsState().value
    val user = viewModel.user.collectAsState().value
    val latest = viewModel.latest.collectAsState().value
    val sale = viewModel.sale.collectAsState().value
    LazyColumn() {
        item {
            TopBarHome(user)
        }
        item {
            SearchPanel(
                value = search,
                modifier = Modifier
                    .padding(vertical = 9.dp, horizontal = 26.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(SearchField)
                    .fillMaxWidth(),
                hint = stringResource(
                    R.string.search_hint
                ),
                onValueChange = viewModel::updateSearch,
            )
        }
        item { Spacer(modifier = Modifier.height(9.dp)) }
        item { CategoryRow() }
        item { Spacer(modifier = Modifier.height(22.dp)) }
        item { if (latest is DataResource.Success) Latest(latest.data) }
        item { Spacer(modifier = Modifier.height(17.dp)) }
        item { if (sale is DataResource.Success) Sale(sale.data) }
        item { Spacer(modifier = Modifier.height(17.dp)) }
        item { Brands() }
    }


}




