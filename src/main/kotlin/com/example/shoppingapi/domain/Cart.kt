package com.example.shoppingapi.domain

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Cart(
    @Id
    var id: String?,
    val cartItems: List<CartItem> = listOf()
)