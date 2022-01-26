package com.example.shoppingapi.domain

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
        save.subscribe(
                System.out::println
        )

        val findAll = itemRepository.findAll()
        findAll.subscribe(
                System.out::println
        )
    }
}