package com.example.shoppingapi.domain.repository

import com.example.shoppingapi.domain.document.Item
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface ItemRepository : ReactiveMongoRepository<Item, String>{
}