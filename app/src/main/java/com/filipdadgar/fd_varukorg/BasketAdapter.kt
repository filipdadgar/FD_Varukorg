package com.filipdadgar.fd_varukorg

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filipdadgar.fd_varukorg.databinding.ItemBasketBinding
import java.text.DecimalFormat

class BasketAdapter(private val context: Context) : RecyclerView.Adapter<BasketAdapter.BasketViewHolder>() {
    
    private var basketItems = listOf<BasketItem>()
    
    fun updateItems(items: List<BasketItem>) {
        basketItems = items
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasketViewHolder {
        val binding = ItemBasketBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasketViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: BasketViewHolder, position: Int) {
        holder.bind(basketItems[position])
    }
    
    override fun getItemCount(): Int = basketItems.size
    
    inner class BasketViewHolder(private val binding: ItemBasketBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(basketItem: BasketItem) {
            binding.tvProductName.text = context.getString(basketItem.product.nameResId)
            binding.tvProductPrice.text = context.getString(basketItem.product.priceResId)
            binding.tvQuantity.text = context.getString(R.string.item_quantity, basketItem.quantity)
            
            val price = BasketManager.getItemPrice(basketItem.product)
            val total = price * basketItem.quantity
            val formattedTotal = DecimalFormat("#,###").format(total)
            binding.tvItemTotal.text = context.getString(R.string.item_total, formattedTotal)
            
            binding.ivProductImage.setImageResource(basketItem.product.imageResId)
        }
    }
}