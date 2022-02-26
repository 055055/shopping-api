package com.example.shoppingapi.service

import com.example.shoppingapi.domain.CartItem
import com.example.shoppingapi.domain.document.Cart
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ShoppingAggregation(
    private val itemService: ItemService,
    private val cartService: CartService,
) {

    fun addToCart(cartId:String, itemId:String ):Mono<Cart> = cartService.addToCart(cartId, itemId)
}