package com.example.shoppingapi.domain

import com.example.shoppingapi.config.logger
import com.example.shoppingapi.domain.document.Item
import com.example.shoppingapi.domain.repository.ItemRepository
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class Test501(
    private val itemRepository: ItemRepository
) {

    @Bean
    fun test() {
        val save = itemRepository.save(
            Item(null, "dd", 12.5)
        )
        save.subscribe {
            logger.info { it }
        }
        val findAll = itemRepository.findAll()
        findAll.subscribe {
            logger.info { it }
        }
    }
}