package com.example.feature_f.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feature_f.R
import com.example.feature_f.presentation.components.*
import com.example.navigation.navigateToDetails
import com.example.remote.data.remote.DataResource
import com.example.theme.SearchField


@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = hiltViewModel()) {
    val search = viewModel.search.collectAsState().value
    val state = viewModel.state.collectAsState().value
    val user = viewModel.user.collectAsState().value
    val words = viewModel.searchWords.collectAsState().value
    var isExpanded = words.isNotEmpty() && search.isNotEmpty()

    Box() {
    LazyColumn() {
        item {
            if (user is DataResource.Success) TopBarHome(user.data)
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
        
        when(state){
            is DataResource.Loading -> item { Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            } }
            is DataResource.Failure -> {
                item { Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                    Text(text = state.errorBody)
                } }
            }
            is DataResource.Success ->{
                item {  Latest(state.data.latest){ navController.navigateToDetails() } }
                item { Spacer(modifier = Modifier.height(17.dp)) }
                item {  Sale(state.data.sale) { navController.navigateToDetails() } }
                item { Spacer(modifier = Modifier.height(17.dp)) }
                item { Brands(state.data.brands) }
            }
        }
       
    }

        if (isExpanded){
            Column(modifier = Modifier.align(Alignment.TopCenter).padding(32.dp).fillMaxWidth().padding(top = 75.dp).shadow(4.dp, MaterialTheme.shapes.medium).clip(MaterialTheme.shapes.medium).background(MaterialTheme.colors.background)) {
                words.forEach { label ->
                    Box(modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 4.dp)
                        .clickable {
                            viewModel.updateSearch(label)
                            isExpanded = false
                        }
                    ) {
                        Text(text = label, style = MaterialTheme.typography.h2)
                    }

                }
            }
        }
}

}







