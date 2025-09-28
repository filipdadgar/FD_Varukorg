package com.filipdadgar.fd_varukorg

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.filipdadgar.fd_varukorg.databinding.ActivityShopBinding

class ShopActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityShopBinding
    private lateinit var products: List<Product>
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        initializeProducts()
        setupClickListeners()
    }
    
    private fun initializeProducts() {
        products = listOf(
            Product(1, R.string.product_smartphone, R.string.product_smartphone_price, R.drawable.ic_launcher_foreground, 8999.0, 899.0),
            Product(2, R.string.product_laptop, R.string.product_laptop_price, R.drawable.ic_launcher_foreground, 15999.0, 1599.0),
            Product(3, R.string.product_headphones, R.string.product_headphones_price, R.drawable.ic_launcher_foreground, 1299.0, 129.0),
            Product(4, R.string.product_watch, R.string.product_watch_price, R.drawable.ic_launcher_foreground, 2499.0, 249.0)
        )
    }
    
    private fun setupClickListeners() {
        binding.btnBackToMain.setOnClickListener {
            finish()
        }
        
        binding.btnGoToBasket.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity(intent)
        }
        
        // Product 1 - Smartphone
        binding.btnAddProduct1.setOnClickListener {
            BasketManager.addProduct(products[0])
            Toast.makeText(this, "${getString(products[0].nameResId)} ${getString(R.string.btn_add_to_basket)}", Toast.LENGTH_SHORT).show()
        }
        
        // Product 2 - Laptop
        binding.btnAddProduct2.setOnClickListener {
            BasketManager.addProduct(products[1])
            Toast.makeText(this, "${getString(products[1].nameResId)} ${getString(R.string.btn_add_to_basket)}", Toast.LENGTH_SHORT).show()
        }
        
        // Product 3 - Headphones
        binding.btnAddProduct3.setOnClickListener {
            BasketManager.addProduct(products[2])
            Toast.makeText(this, "${getString(products[2].nameResId)} ${getString(R.string.btn_add_to_basket)}", Toast.LENGTH_SHORT).show()
        }
        
        // Product 4 - Watch
        binding.btnAddProduct4.setOnClickListener {
            BasketManager.addProduct(products[3])
            Toast.makeText(this, "${getString(products[3].nameResId)} ${getString(R.string.btn_add_to_basket)}", Toast.LENGTH_SHORT).show()
        }
    }
}