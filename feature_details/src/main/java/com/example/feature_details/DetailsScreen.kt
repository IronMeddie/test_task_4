package com.example.feature_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feature_details.compoments.BottomAddToCartLayout
import com.example.feature_details.compoments.DetailsImagePager
import com.example.feature_details.compoments.InfoAndColor
import com.example.feature_details.compoments.SmalImagePager
import com.example.theme.DarkBlue
import com.example.utils.DataResource
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.isActive

@OptIn(ExperimentalPagerApi::class)
@Composable
fun DetailsScreen(navController: NavController, viewModel: DetailsViewModel = hiltViewModel()) {
    when (val details = viewModel.details.collectAsState().value) {
        is DataResource.Success -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.padding(top = 25.dp)) {
                    if (details.data.image_urls.isNotEmpty()) {
                        val pagerState = rememberPagerState()
                        val state2 = rememberPagerState()
                        val scope = rememberCoroutineScope()

                        LaunchedEffect(pagerState.currentPage) {
                            if (!state2.isScrollInProgress || !scope.isActive) {
                                snapshotFlow { pagerState.currentPage }.collect { page ->
                                    state2.animateScrollToPage(page)
                                }
                            }

                        }
                        LaunchedEffect(state2.currentPage) {
                            if (!pagerState.isScrollInProgress || !scope.isActive) {
                                snapshotFlow { state2.currentPage }.collect { page ->
                                    pagerState.animateScrollToPage(page)
                                }
                            }

                        }

                        DetailsImagePager(images = details.data.image_urls, pagerState)
                        Spacer(modifier = Modifier.height(35.dp))
                        SmalImagePager(images = details.data.image_urls, state2) {}

                        Spacer(modifier = Modifier.height(21.dp))

                        InfoAndColor(details.data)
                    }

                }
                BottomAddToCartLayout(
                    details.data.price,
                    Modifier
                        .padding(bottom = 31.dp)
                        .fillMaxWidth()
                        .height(120.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(DarkBlue)
                        .align(Alignment.BottomCenter)
                )


                IconButton(
                    onClick = { navController.navigateUp() },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.arrow_back),
                        contentDescription = "icon back"
                    )
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



