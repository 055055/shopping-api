package com.example.shoppingapi.domain.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Item(
    @Id
    val seq: String? = null,
    val name: String,
    val price: Double,
    val isSell: Boolean = true,
)