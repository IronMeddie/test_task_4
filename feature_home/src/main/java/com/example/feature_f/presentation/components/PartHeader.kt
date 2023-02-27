package com.example.feature_f.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_f.R
import com.example.theme.OnotherOneGrey

@Composable
fun PartHeader(title: String, onClikViewAll : () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 11.dp), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(text = title, style = MaterialTheme.typography.h2)
        Text(text = stringResource(R.string.view_all), modifier = Modifier.clickable { onClikViewAll() } , style = MaterialTheme.typography.h2, fontSize = 9.sp, color = OnotherOneGrey)
    }
}