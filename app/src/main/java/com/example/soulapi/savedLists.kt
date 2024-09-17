package com.example.soulapi

import com.example.soulapi.model.CartModel
import com.example.soulapi.model.ProfileModelItem
import kotlinx.coroutines.flow.MutableStateFlow

object savedLists {
    val cartList = mutableListOf<CartModel>()
    val carlistObversable = MutableStateFlow<List<CartModel>>(emptyList())
    fun giveProfileItems(
        orders: String,
        mydetails: String,
        address: String,
        payment: String,
        notification: String
    ): List<ProfileModelItem> {

        return listOf(
            ProfileModelItem(
                image = R.drawable.ic_orders,
                title = orders
            ),
            ProfileModelItem(
                image = R.drawable.ic_my_details,
                title = mydetails
            ),
            ProfileModelItem(
                image = R.drawable.ic_address,
                title = address
            ),
            ProfileModelItem(
                image = R.drawable.ic_payment,
                title = payment
            ),
            ProfileModelItem(
                image = R.drawable.ic_notification,
                title = notification
            ),
        )
    }
}