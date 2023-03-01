package com.example.feature_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feature_details.compoments.BottomAddToCartLayout
import com.example.feature_details.compoments.InfoAndColor
import com.example.remote.data.remote.DataResource
import com.example.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.isActive

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsScreen(navController: NavController,viewModel: DetailsViewModel = hiltViewModel()) {
    when (val details = viewModel.details.collectAsState().value) {
        is DataResource.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.padding(top = 25.dp)) {
                    if (details.data.image_urls.isNotEmpty()) {
                        val pagerState = rememberPagerState()
                        val state2 = rememberPagerState()
                        val scope = rememberCoroutineScope()

                        LaunchedEffect(pagerState.currentPage) {
                            if (!state2.isScrollInProgress || !scope.isActive){
                                snapshotFlow { pagerState.currentPage }.collect { page ->
                                    state2.animateScrollToPage(page)
                                }
                            }

                        }
                        LaunchedEffect(state2.currentPage) {
                            if (!pagerState.isScrollInProgress || !scope.isActive ){
                                snapshotFlow { state2.currentPage }.collect { page ->
                                    pagerState.animateScrollToPage(page)
                                }
                            }

                        }

                        DetailsImagePager(images = details.data.image_urls, pagerState)
                        Spacer(modifier = Modifier.height(35.dp))
                        SmalImagePager(images = details.data.image_urls, state2){}

                        Spacer(modifier = Modifier.height(21.dp))

                     InfoAndColor(details.data)
                    }

                }
                BottomAddToCartLayout(details.data.price,
                    Modifier
                        .padding(bottom = 31.dp)
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(DarkBlue)
                        .align(Alignment.BottomCenter))


                IconButton(
                    onClick = { navController.navigateUp() },
                ) {
                    Icon(painter = painterResource(id = R.drawable.arrow_back), contentDescription = "icon back")
                }
            }
        }
        is DataResource.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is DataResource.Failure -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = details.errorBody)
            }
        }
    }

}



