package com.filipdadgar.fd_varukorg

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.filipdadgar.fd_varukorg.databinding.ActivityBasketBinding
import java.text.DecimalFormat

class BasketActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityBasketBinding
    private lateinit var basketAdapter: BasketAdapter
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupRecyclerView()
        setupClickListeners()
        updateUI()
    }
    
    private fun setupRecyclerView() {
        basketAdapter = BasketAdapter(this)
        binding.recyclerViewBasket.apply {
            layoutManager = LinearLayoutManager(this@BasketActivity)
            adapter = basketAdapter
        }
    }
    
    private fun setupClickListeners() {
        binding.btnBackToShop.setOnClickListener {
            finish()
        }
        
        binding.btnGoToConfirm.setOnClickListener {
            if (BasketManager.isEmpty()) {
                return@setOnClickListener
            }
            val intent = Intent(this, ConfirmActivity::class.java)
            startActivity(intent)
        }
    }
    
    private fun updateUI() {
        val basketItems = BasketManager.getBasketItems()
        basketAdapter.updateItems(basketItems)
        
        if (basketItems.isEmpty()) {
            binding.tvEmptyBasket.visibility = android.view.View.VISIBLE
            binding.recyclerViewBasket.visibility = android.view.View.GONE
            binding.btnGoToConfirm.isEnabled = false
        } else {
            binding.tvEmptyBasket.visibility = android.view.View.GONE
            binding.recyclerViewBasket.visibility = android.view.View.VISIBLE
            binding.btnGoToConfirm.isEnabled = true
        }
        
        val totalPrice = BasketManager.getTotalPrice()
        val formattedPrice = DecimalFormat("#,###").format(totalPrice)
        binding.tvTotalPrice.text = getString(R.string.total_price, formattedPrice)
    }
    
    override fun onResume() {
        super.onResume()
        updateUI()
    }
}