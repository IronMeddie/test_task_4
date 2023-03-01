package com.example.feature_details.compoments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_details.R
import com.example.theme.*

@Composable
fun BottomAddToCartLayout(price: Int, modifier: Modifier = Modifier){
    var currentprice by remember{ mutableStateOf(price) }
    Box(modifier = modifier)
    {
        Text(text = stringResource(R.string.quantity), style = MaterialTheme.textStyles.AddToCartQuantity, modifier = Modifier.padding(start = 24.dp, top = 14.dp))
        Row(modifier = Modifier.padding(MaterialTheme.dimens.DetailsPlusButtonsPadding)) {
            Button(onClick = { if (currentprice != price )currentprice -= price }, modifier = Modifier
                .width(MaterialTheme.dimens.DetailsPlusButtonWidth)
                .height(MaterialTheme.dimens.DetailsPlusButtonHeight), shape = MaterialTheme.dimens.DetailsPlusButtonShape
            ) {
                Icon(painterResource(id = R.drawable.vector__15_), contentDescription = "minus")
            }
            Spacer(modifier = Modifier.width(MaterialTheme.dimens.DetailsPlusButtonsSpacer))
            Button(onClick = { currentprice += price } , modifier = Modifier
                .width(MaterialTheme.dimens.DetailsPlusButtonWidth)
                .height(MaterialTheme.dimens.DetailsPlusButtonHeight) , shape = MaterialTheme.dimens.DetailsPlusButtonShape) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "plus")
            }
        }

        Button(onClick = { currentprice += price }, modifier = Modifier
            .padding(MaterialTheme.dimens.AddToCartButtonPaddings)
            .width(MaterialTheme.dimens.AddToCartButtonWidth)
            .height(MaterialTheme.dimens.AddToCartButtonHeight)
            .align(Alignment.TopEnd)
        ) {
            Row() {
                Text(text = "# " + currentprice , color = AddToCartCount, style = MaterialTheme.textStyles.AddToCartButtonText)
                Spacer(modifier = Modifier.width(30.dp))
                Text(text = "ADD TO CART" , style = MaterialTheme.textStyles.AddToCartButtonText)
            }
        }

    }
}