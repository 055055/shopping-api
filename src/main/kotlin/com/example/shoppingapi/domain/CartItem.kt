package com.example.shoppingapi.domain

import com.example.shoppingapi.domain.document.Item

data class CartItem(
    val item: Item,
    val quantity: Int = 1,
)