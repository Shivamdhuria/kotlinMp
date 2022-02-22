package com.surrus.common.domain

data class Dog(
    val breed: String,
    val rating: String,
    val imageUrl: String,
    val timestamp: String = ""
)
