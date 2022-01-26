package com.example.shoppingapi.domain.repository

import com.example.shoppingapi.domain.Cart
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface CartRepository : ReactiveMongoRepository<Cart, String> {
}