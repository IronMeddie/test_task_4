package com.example.feature_sign_in.screens.sign_in

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.feature_sign_in.R
import com.example.feature_sign_in.screens.welcome_back.Logged
import com.example.navigation.navigateToMainScreen
import com.example.navigation.navigateToSignUp
import com.example.theme.AppLink
import com.example.theme.GreyField
import com.example.theme.GreyText
import kotlinx.coroutines.flow.collectLatest


@Composable
fun SignInScreen(navController: NavController, viewModel: SignInViewModel = hiltViewModel()) {
    val firstName = viewModel.firstName.collectAsState().value
    val lastName = viewModel.lastName.collectAsState().value
    val email = viewModel.email.collectAsState().value

    var isError by remember { mutableStateOf("") }
    LaunchedEffect(key1 = true) {
        viewModel.eventFLow.collectLatest { logged ->
            when (logged) {
                is Logged.Success -> {
                    navController.navigateToMainScreen()
                }

                is Logged.Failure -> {
                    isError = logged.message
                }
            }

        }
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 43.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.sign_in), style = MaterialTheme.typography.h4)
            Spacer(modifier = Modifier.height(60.dp))
            MyTextField(
                firstName, modifier = Modifier
                    .fillMaxWidth()
                    .height(29.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(GreyField), stringResource(R.string.first_name)
            ) { viewModel.updateField(UpdateField.First(it)) }
            Spacer(modifier = Modifier.height(35.dp))
            MyTextField(
                lastName, modifier = Modifier
                    .fillMaxWidth()
                    .height(29.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(GreyField), stringResource(R.string.last_name)
            ) { viewModel.updateField(UpdateField.Last(it)) }
            Spacer(modifier = Modifier.height(35.dp))
            MyTextField(
                email, modifier = Modifier
                    .fillMaxWidth()
                    .height(29.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(GreyField), stringResource(R.string.email)
            ) {
                viewModel.updateField(UpdateField.Email(it))
            }
            AnimatedVisibility(visible = isError.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = isError)
            }
            Spacer(modifier = Modifier.height(35.dp))
            Button(
                onClick = {
                    viewModel.insert()
                }, modifier = Modifier
                    .fillMaxWidth()
                    .height(46.dp)
            ) {
                Text(text = stringResource(R.string.sign_in))
            }
            Spacer(modifier = Modifier.height(15.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigateToSignUp() }) {
                Text(
                    text = stringResource(R.string.already_have_acc),
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W500,
                    lineHeight = 12.19.sp
                )
                Text(
                    text = stringResource(R.string.log_in),
                    color = AppLink,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.W500,
                    lineHeight = 12.19.sp
                )
            }
            Spacer(modifier = Modifier.height(60.dp))
            SignWith(R.drawable.google, stringResource(R.string.sign_with_google))
            Spacer(modifier = Modifier.height(28.dp))
            SignWith(R.drawable.apple, stringResource(R.string.sign_with_apple))
        }
    }


}


@Composable
fun MyTextField(
    value: String,
    modifier: Modifier = Modifier,
    hint: String,
    onValueChange: (String) -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        BasicTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            modifier = Modifier
                .matchParentSize()
                .padding(horizontal = 16.dp, vertical = 6.dp),
        )
        if (value.isEmpty()) Text(
            text = hint,
            color = GreyText,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun SignWith(IconRes: Int, text: String) {
    Row(modifier = Modifier
        .clickable { }
        .padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = IconRes), contentDescription = "icon sign with")
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, fontWeight = FontWeight.W500, fontSize = 12.sp)
    }

}