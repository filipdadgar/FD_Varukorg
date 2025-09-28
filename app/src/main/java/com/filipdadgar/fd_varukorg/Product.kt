package com.filipdadgar.fd_varukorg

data class Product(
    val id: Int,
    val nameResId: Int,
    val priceResId: Int,
    val imageResId: Int,
    val priceSEK: Double,
    val priceUSD: Double
)

data class BasketItem(
    val product: Product,
    var quantity: Int
)