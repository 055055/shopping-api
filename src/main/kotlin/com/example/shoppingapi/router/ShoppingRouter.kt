package com.example.shoppingapi.router

import com.example.shoppingapi.handler.ShoppingHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse


@Component
class ShoppingRouter(
    private val shoppingHandler: ShoppingHandler
) {

    @Bean
    fun route(): RouterFunction<ServerResponse> {
        return shoppingHandler.getRouteRule()
    }
}