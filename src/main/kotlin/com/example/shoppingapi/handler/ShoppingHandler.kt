package com.example.shoppingapi.handler

import com.example.shoppingapi.domain.document.Item
import com.example.shoppingapi.dto.ItemSaveReqDto
import com.example.shoppingapi.service.ItemService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono


@Component
class ShoppingHandler(
    private val itemService: ItemService
) {

    fun saveItem(serverRequest: ServerRequest): Mono<ServerResponse> {
        val itemSaveReq = serverRequest.bodyToMono(ItemSaveReqDto::class.java)

        return itemSaveReq.flatMap { request ->
            ServerResponse.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(itemService.save(request), Item::class.java)
        }
    }

    fun getItemById(serverRequest: ServerRequest): Mono<ServerResponse> {

       return ServerResponse.status(HttpStatus.OK)
           .contentType(MediaType.APPLICATION_JSON)
           .body(itemService.findItemById(serverRequest.pathVariable("itemId")), Item::class.java)
    }

    fun getRouteRule(): RouterFunction<ServerResponse> {
        return RouterFunctions
            .route(RequestPredicates.POST("/item"), this::saveItem)
            .andRoute(RequestPredicates.GET("/item/{itemId}"), this::getItemById)
    }
}