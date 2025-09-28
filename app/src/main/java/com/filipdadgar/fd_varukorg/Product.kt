package com.filipdadgar.fd_varukorg

data class Product(
    val id: Int,
    val nameResId: Int,
    val priceResId: Int,
    val imageResId: Int,
    val priceSEK: Double,
    val priceUSD: Double
) {
    companion object {
        fun createFromArrays(
            context: android.content.Context,
            position: Int,
            nameArray: Array<String>,
            pricesSEK: Array<String>,
            pricesUSD: Array<String>,
            imageNames: Array<String>
        ): Product {
            val imageResId = context.resources.getIdentifier(
                imageNames[position],
                "drawable",
                context.packageName
            )
            
            // Create dynamic string resources for names and prices
            val nameResId = when (position) {
                0 -> R.string.product_smartphone
                1 -> R.string.product_laptop  
                2 -> R.string.product_headphones
                3 -> R.string.product_watch
                else -> R.string.product_smartphone
            }
            
            val priceResId = when (position) {
                0 -> R.string.product_smartphone_price
                1 -> R.string.product_laptop_price
                2 -> R.string.product_headphones_price  
                3 -> R.string.product_watch_price
                else -> R.string.product_smartphone_price
            }
            
            return Product(
                id = position + 1,
                nameResId = nameResId,
                priceResId = priceResId,
                imageResId = imageResId,
                priceSEK = pricesSEK[position].toDouble(),
                priceUSD = pricesUSD[position].toDouble()
            )
        }
    }
}

data class BasketItem(
    val product: Product,
    var quantity: Int
)