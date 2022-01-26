package com.example.shoppingapi.handler

import com.example.shoppingapi.config.logger
import com.example.shoppingapi.dto.ItemSaveReqDto
import com.example.shoppingapi.service.ItemService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono


@Component
class ShoppingHandler(
    private val itemService: ItemService
) {

    fun saveItem(serverRequest: ServerRequest): Mono<ServerResponse> {
        logger.info{serverRequest.bodyToMono(String::class.java)}
        return ServerResponse.ok().body(serverRequest.bodyToMono(ItemSaveReqDto::class.java).map {
            itemService.save(it)
        })
    }

    fun getRouteRule(): RouterFunction<ServerResponse> {
        return RouterFunctions
            .route(RequestPredicates.POST("/item"), this::saveItem)
    }
}