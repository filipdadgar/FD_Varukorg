package com.filipdadgar.fd_varukorg

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.filipdadgar.fd_varukorg.databinding.ActivityConfirmBinding

class ConfirmActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityConfirmBinding
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        setupClickListeners()
    }
    
    private fun setupClickListeners() {
        binding.btnCancel.setOnClickListener {
            Toast.makeText(this, getString(R.string.order_cancelled), Toast.LENGTH_SHORT).show()
            navigateToMain()
        }
        
        binding.btnConfirmOrder.setOnClickListener {
            if (validateFields()) {
                BasketManager.clearBasket()
                Toast.makeText(this, getString(R.string.order_confirmed), Toast.LENGTH_LONG).show()
                navigateToMain()
            } else {
                Toast.makeText(this, getString(R.string.fill_all_fields), Toast.LENGTH_SHORT).show()
            }
        }
    }
    
    private fun validateFields(): Boolean {
        return binding.etFirstName.text.toString().trim().isNotEmpty() &&
                binding.etLastName.text.toString().trim().isNotEmpty() &&
                binding.etStreetAddress.text.toString().trim().isNotEmpty() &&
                binding.etCity.text.toString().trim().isNotEmpty() &&
                binding.etCardNumber.text.toString().trim().isNotEmpty()
    }
    
    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
        startActivity(intent)
        finish()
    }
}