package com.example.shoppingapi.domain

data class CartItem(
    val item: Item,
    val quantity: Int = 1,
)