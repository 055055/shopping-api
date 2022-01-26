package com.example.shoppingapi.service

import com.example.shoppingapi.domain.document.Item
import com.example.shoppingapi.domain.repository.ItemRepository
import com.example.shoppingapi.dto.ItemSaveReqDto
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ItemService(
    private val itemRepository: ItemRepository
) {

    fun save(itemSaveReqDto: ItemSaveReqDto): Mono<Item> =
        itemRepository.save(Item(name = itemSaveReqDto.name, price = itemSaveReqDto.price))
}