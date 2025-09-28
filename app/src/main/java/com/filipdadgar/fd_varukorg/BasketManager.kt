package com.filipdadgar.fd_varukorg

import java.util.Locale

object BasketManager {
    private val basketItems = mutableListOf<BasketItem>()
    
    fun addProduct(product: Product, quantity: Int = 1) {
        val existingItem = basketItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            basketItems.add(BasketItem(product, quantity))
        }
    }
    
    fun getBasketItems(): List<BasketItem> = basketItems.toList()
    
    fun getTotalPrice(): Double {
        val isSwedish = Locale.getDefault().language == "sv"
        return basketItems.sumOf { 
            val price = if (isSwedish) it.product.priceSEK else it.product.priceUSD
            price * it.quantity 
        }
    }
    
    fun getItemPrice(product: Product): Double {
        val isSwedish = Locale.getDefault().language == "sv"
        return if (isSwedish) product.priceSEK else product.priceUSD
    }
    
    fun clearBasket() {
        basketItems.clear()
    }
    
    fun isEmpty(): Boolean = basketItems.isEmpty()
}