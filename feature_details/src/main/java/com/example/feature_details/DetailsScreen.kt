package com.example.feature_details

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
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
                        SmalImagePager(images = details.data.image_urls, state2){

                        }

                        Spacer(modifier = Modifier.height(21.dp))

                        Column(modifier =  Modifier.padding(horizontal = 24.dp)) {
                            Row(modifier = Modifier
                                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text(text = details.data.name, style = MaterialTheme.typography.h2, fontWeight = FontWeight.W600, fontSize = 17.sp, color = DetailsTitle)
                                Text(text ="$ " + details.data.price, style = MaterialTheme.typography.h2, fontWeight = FontWeight.W600, fontSize = 13.sp, color = DetailsTitle)
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(text = details.data.description, style = MaterialTheme.typography.h2, fontWeight = FontWeight.W400, fontSize = 9.sp, color = OnotherOneGrey)
                            Spacer(modifier = Modifier.height(14.dp))
                            Row() {
                                AsyncImage(model =  R.drawable.star, contentDescription = "star", modifier = Modifier.size(10.dp))
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(text = details.data.rating.toString(), style = MaterialTheme.typography.h2, fontWeight = FontWeight.W600, fontSize = 9.sp, color = DetailsTitle)
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(text = "(${details.data.number_of_reviews} ${stringResource(R.string.reviews)})" , style = MaterialTheme.typography.h2, fontWeight = FontWeight.W400, fontSize = 9.sp, color = OnotherOneGrey)
                            }
                            Spacer(modifier = Modifier.height(14.dp))
                            Text(text = stringResource(R.string.color), style = MaterialTheme.typography.h2, fontWeight = FontWeight.W600, fontSize = 10.sp, color = DetailsColor)
                            Spacer(modifier = Modifier.height(11.dp))
                            if (details.data.colors.isNotEmpty()){
                                Row() {
                                    var currentColor by remember { mutableStateOf(details.data.colors[0]) }
                                    details.data.colors.forEach {
                                        val modifier = if (currentColor == it) {
                                            Modifier
                                                .width(34.dp)
                                                .height(26.dp)
                                                .clip(
                                                    RoundedCornerShape(9.dp)
                                                )
                                                .background(Color(it.toColorInt()))
                                                .border(2.dp, ColorBorder, RoundedCornerShape(9.dp))

                                        } else {
                                            Modifier
                                                .width(34.dp)
                                                .height(26.dp)
                                                .clip(
                                                    RoundedCornerShape(9.dp)
                                                )
                                                .background(Color(it.toColorInt()))
                                                .clickable { currentColor = it }
                                        }
                                        Box(modifier = modifier)
                                        Spacer(modifier = Modifier.width(14.dp))
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                        }
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
                FavoriteShareButtons(modifier = Modifier
                    .padding(end = 42.dp, top = 182.dp)
                    .width(42.dp)
                    .height(95.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(AppIconButton)
                    .align(Alignment.TopEnd), onClickFavorite = {}, onClickShare = {})

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

@Composable
fun FavoriteShareButtons(modifier: Modifier = Modifier, onClickShare :() -> Unit, onClickFavorite : () -> Unit){
    Column(modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(painter = painterResource(id = R.drawable.favorite), contentDescription = "favorite button", tint= IconButton, modifier = Modifier
            .clickable { onClickFavorite() }
            .padding(13.dp))
        Spacer(modifier = Modifier
            .width(12.dp)
            .height(1.dp)
            .background(IconButton))
        Icon(painter = painterResource(id = R.drawable.share), contentDescription = "share button",tint= IconButton, modifier = Modifier
            .clickable { onClickShare() }
            .padding(13.dp))
    }
}

@Composable
fun BottomAddToCartLayout(price: Int, modifier: Modifier = Modifier){
    var currentprice by remember{ mutableStateOf(price) }
    Box(modifier = modifier)
         {
        Text(text = stringResource(R.string.quantity), style = MaterialTheme.typography.h2, color = OnotherOneGrey, fontSize = 9.sp, modifier = Modifier.padding(start = 24.dp, top = 14.dp))
        Row(modifier = Modifier.padding(start = 24.dp, top = 38. dp)) {
            Button(onClick = { if (currentprice != price )currentprice -= price }, modifier = Modifier
                .width(38.dp)
                .height(22.dp), shape = RoundedCornerShape(8.dp)) {
                Icon(painterResource(id = R.drawable.vector__15_), contentDescription = "minus")
            }
            Spacer(modifier = Modifier.width(20.dp))
            Button(onClick = { currentprice += price } , modifier = Modifier
                .width(38.dp)
                .height(22.dp) , shape = RoundedCornerShape(8.dp)) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "plus")
            }
        }

        Button(onClick = { currentprice += price }, modifier = Modifier
            .padding(end = 24.dp, top = 19.dp, bottom = 19.dp)
            .width(170.dp)
            .height(44.dp)
            .align(Alignment.TopEnd)
            ) {
            Row() {
                Text(text = "# " + currentprice , color = AddToCartCount, style = MaterialTheme.typography.h2, fontWeight = FontWeight.W600, fontSize = 8.sp)
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "ADD TO CART" , style = MaterialTheme.typography.h2, fontWeight = FontWeight.W600, fontSize = 8.sp, color = White)
            }
        }

    }
}