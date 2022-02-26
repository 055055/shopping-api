package com.example.shoppingapi.service

import com.example.shoppingapi.domain.CartItem
import com.example.shoppingapi.domain.document.Cart
import com.example.shoppingapi.domain.repository.CartRepository
import com.example.shoppingapi.domain.repository.ItemRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CartService(
    private val cartRepository: CartRepository,
    private val itemRepository: ItemRepository,
) {

    fun getCart(cartId: String) =
        cartRepository.findById(cartId)
            .defaultIfEmpty(Cart())

    fun addToCart(cartId: String, itemId: String): Mono<Cart> {
        return getCart(cartId)
            .flatMap { cart ->
                cart.cartItems.stream()
                    .filter { cartItem -> cartItem.item.id.equals(itemId) }
                    .findAny()
                    .let { cartItem ->
                        if (cartItem.isPresent) {
                            addQuantity(cartItem.get()).then(Mono.just(cart))
                        } else {
                            addCartItem(cart, itemId).then(Mono.just(cart))
                        }
                    }
            }
            .flatMap {
                cartRepository.save(it)
            }
    }

    fun addQuantity(cartItem: CartItem): Mono<Void> {
        cartItem.increment()
        return Mono.empty()
    }

    fun addCartItem(cart: Cart, itemId: String): Mono<Void> =
        itemRepository.findById(itemId)
            .flatMap {
                cart.cartItems.add(CartItem(it))
                Mono.empty()
            }
}