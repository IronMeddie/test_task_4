package com.example.feature_profile

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.navigation.navigateToLoginScreen
import com.example.theme.Border
import com.example.theme.GreyIconBack
import com.example.theme.NameProfile
import com.example.theme.OnotherOneGrey
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController, viewModel: ProfileViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val context = LocalContext.current

    val contract =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia(),
            onResult = { uri ->
                val flag = Intent.FLAG_GRANT_READ_URI_PERMISSION
                context.contentResolver.takePersistableUriPermission(
                    uri ?: return@rememberLauncherForActivityResult, flag
                )

                scope.launch {
                    selectedImageUri = uri
                    viewModel.saveAvatar(uri)
                }
            })

    val user = viewModel.user.collectAsState().value


    Scaffold(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.padding(it)) {
            item { ProfileTopBar() { navController.navigateUp() } }

            item {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AsyncImage(
                        model = if (selectedImageUri == null) {
                            if (!user?.avatar.isNullOrBlank()) user?.avatar else R.drawable.avatar
                        } else selectedImageUri, contentDescription = "user avatar",
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(61.dp)
                            .border(1.dp, Border, CircleShape), contentScale = ContentScale.Crop
                    )
                    Text(
                        text = stringResource(R.string.change_photo),
                        fontWeight = FontWeight.W500,
                        fontSize = 8.sp,
                        color = OnotherOneGrey,
                        modifier = Modifier.clickable {
                            contract.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(17.dp))
                    Text(
                        text = stringResource(R.string.name),
                        style = MaterialTheme.typography.h3,
                        fontSize = 15.sp,
                        color = NameProfile
                    )
                }
            }

            item { Spacer(modifier = Modifier.height(36.dp)) }
            item {
                Button(
                    onClick = {
//                        contract.launch(
//                            PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly),
//                        )
                    }, modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 43.dp)
                        .height(40.dp)
                ) {
                    Row() {
                        Icon(
                            painter = painterResource(id = R.drawable.share),
                            contentDescription = "upload icon"
                        )
                        Spacer(modifier = Modifier.width(30.dp))
                        Text(text = stringResource(R.string.upload_item))
                        Spacer(modifier = Modifier.width(45.dp))
                    }
                }
                Spacer(modifier = Modifier.height(4.dp))
            }



            item { ProfileListItem(stringResource(R.string.trade_store), R.drawable.credit_card, {}) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_next),
                    contentDescription = "arrow"
                )
            }
            }
            item { ProfileListItem(stringResource(R.string.payment_method), R.drawable.credit_card, {}) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_next),
                    contentDescription = "arrow"
                )
            }
            }
            item { ProfileListItem(stringResource(R.string.balance), R.drawable.credit_card, {}) {
                Text(text = "$ 1593", fontWeight = FontWeight.W500,
                    fontSize = 14.sp, color = MaterialTheme.colors.onBackground)
            }
            }
            item { ProfileListItem(stringResource(R.string.trade_history), R.drawable.credit_card, {}) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_next),
                    contentDescription = "arrow"
                )
            }
            }
            item { ProfileListItem(stringResource(R.string.restore_purchase), R.drawable.group_92, {}) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_next),
                    contentDescription = "arrow"
                )
            }
            }
            item { ProfileListItem(stringResource(R.string.help), R.drawable.help, {}) {} }
            item { ProfileListItem(stringResource(R.string.log_out), R.drawable.log_in, {
                viewModel.logOut()
                navController.navigateToLoginScreen()
            }) {} }

        }
    }
}

@Composable
fun ProfileTopBar(onClickBack: () -> Unit) {
    Box(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()

    ) {
        IconButton(onClick = onClickBack, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "icon back")
        }
        Text(
            text = stringResource(R.string.profile),
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.h3,
            fontSize = 15.sp
        )
    }
}


@Composable
fun ProfileListItem(
    title: String,
    icon: Int,
    onClick : () -> Unit,
    endElement: @Composable () -> Unit,

) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 32.dp, vertical = 10.dp)
        .clickable { onClick() }) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(GreyIconBack), contentAlignment = Alignment.Center) {
                Icon(painter = painterResource(id = icon), contentDescription = "profile list item icon")
        }
        Spacer(modifier = Modifier.width(6.dp))
        Text(text = title, fontWeight = FontWeight.W500,
            fontSize = 14.sp, color = MaterialTheme.colors.onBackground)

    }
        Box(modifier = Modifier
            .align(Alignment.CenterEnd)
            .padding(end = 13.dp)) {
            endElement()
        }
    }
}


















