package com.example.shoppingapi.domain.document

import com.example.shoppingapi.domain.CartItem
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Cart(
    @Id
    val id: String? = null,
    val cartItems: MutableList<CartItem> = mutableListOf()
)