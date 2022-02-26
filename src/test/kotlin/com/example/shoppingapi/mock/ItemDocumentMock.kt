package com.example.shoppingapi.mock

import com.example.shoppingapi.domain.document.Item

fun itemMock(
    name:String = "test-iten",
    price:Double = 12.0,
): Item = Item(name = name, price =price)