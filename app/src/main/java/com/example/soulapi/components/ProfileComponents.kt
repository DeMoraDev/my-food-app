package com.example.soulapi.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.soulapi.R
import com.example.soulapi.model.ProfileModelItem
import com.example.soulapi.savedLists

@Composable
fun ProfileCard(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
        ) {
            Image(
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .align(Alignment.Center),
                painter = painterResource(id = R.drawable.profilepic),
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "María García",  //TODO llamar al nombre del usuario o invitado
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            color = Color.Black
        )
    }
}
@Composable
fun LogOutButton(
    modifier: Modifier = Modifier,
    onClickLogOut: () -> Unit
) {
    Button(
        modifier = modifier
            .padding(16.dp)
            .height(48.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(2.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color(0xFFFFA500)
        ),
        onClick = { onClickLogOut.invoke() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                modifier = Modifier.weight(1f),
                text = stringResource(id = R.string.logOutButton),
                color = Color.Black,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
            )
            Icon(
                imageVector = Icons.Default.ExitToApp,
                contentDescription = "Logout button text",
                tint = Color(0xFFFFA500)
            )
        }
    }
}

@Composable
fun ItemsProfile(
    modifier: Modifier = Modifier,
    profileItems: ProfileModelItem
) {
    Column {
        Divider(modifier = Modifier.height(1.dp), color = Color.Gray)

        Row(
            modifier = modifier
                .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                .fillMaxWidth()
        ) {
            Image(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = profileItems.image),
                contentDescription = "Icons for list of Profile items"
            )
            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(start = 8.dp)
                    .weight(1f),
                text = profileItems.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Icon(
                modifier = Modifier.align(Alignment.CenterVertically),
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Arrow Icon"
            )
        }
    }
}

@Composable
fun ListProfileContent(
    modifier: Modifier = Modifier
) {

    val profileItems = savedLists.giveProfileItems(
        orders = stringResource(id = R.string.profileItemOrders),
        mydetails = stringResource(id = R.string.profileItemMyDetails),
        address = stringResource(id = R.string.profileItemAddress),
        payment = stringResource(id = R.string.profileItemPayment),
        notification = stringResource(id = R.string.profileItemNotifications)
    )

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(profileItems) { item ->
            ItemsProfile(profileItems = item)
        }
    }

    Spacer(modifier = Modifier.height(9.dp))

    Divider(modifier = Modifier.height(1.dp), color = Color.Gray)
}