package com.example.shoppingapi.domain

import com.example.shoppingapi.domain.document.Item

data class CartItem(
    var item: Item,
    var quantity: Double = 1.0,
){

    fun increment()  {
        this.quantity += 1.0
    }
}
