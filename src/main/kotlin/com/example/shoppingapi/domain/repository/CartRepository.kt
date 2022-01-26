package com.example.shoppingapi.domain.repository

import com.example.shoppingapi.domain.document.Cart
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface CartRepository : ReactiveMongoRepository<Cart, String> {
}