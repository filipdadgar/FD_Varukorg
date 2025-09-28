package com.filipdadgar.fd_varukorg

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.filipdadgar.fd_varukorg.databinding.ActivityShopBinding
import java.util.Locale

class ShopActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityShopBinding
    private lateinit var products: List<Product>
    private lateinit var productAdapter: ProductAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        initializeProducts()
        setupClickListeners()
    }
    
    private fun setupRecyclerView() {
        productAdapter = ProductAdapter(this) { product ->
            BasketManager.addProduct(product)
            Toast.makeText(
                this, 
                "${getString(product.nameResId)} ${getString(R.string.btn_add_to_basket)}", 
                Toast.LENGTH_SHORT
            ).show()
        }
        
        binding.recyclerViewProducts.apply {
            layoutManager = LinearLayoutManager(this@ShopActivity)
            adapter = productAdapter
        }
    }
    
    private fun initializeProducts() {
        val productNames = resources.getStringArray(R.array.product_names)
        val imageNames = resources.getStringArray(R.array.product_images)
        
        // Använd rätt pris-array baserat på språk
        val isSwedish = Locale.getDefault().language == "sv"
        val pricesSEK = resources.getStringArray(R.array.product_prices_sek)
        val pricesUSD = if (isSwedish) pricesSEK else try {
            resources.getStringArray(R.array.product_prices_usd)
        } catch (e: Exception) {
            pricesSEK // Fallback till SEK om USD inte finns
        }
        
        products = (0 until productNames.size).map { position ->
            Product.createFromArrays(
                context = this,
                position = position,
                nameArray = productNames,
                pricesSEK = pricesSEK,
                pricesUSD = pricesUSD,
                imageNames = imageNames
            )
        }
        
        productAdapter.updateProducts(products)
    }
    
    private fun setupClickListeners() {
        binding.btnBackToMain.setOnClickListener {
            finish()
        }
        
        binding.btnGoToBasket.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity(intent)
        }
    }
}