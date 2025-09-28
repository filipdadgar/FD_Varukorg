package com.filipdadgar.fd_varukorg

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.filipdadgar.fd_varukorg.databinding.ItemProductBinding

class ProductAdapter(
    private val context: Context,
    private val onAddToBasket: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    
    private var products = listOf<Product>()
    
    fun updateProducts(productList: List<Product>) {
        products = productList
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }
    
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }
    
    override fun getItemCount(): Int = products.size
    
    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        
        fun bind(product: Product) {
            binding.tvProductName.text = context.getString(product.nameResId)
            binding.tvProductPrice.text = context.getString(product.priceResId)
            binding.ivProductImage.setImageResource(product.imageResId)
            
            binding.btnAddToBasket.setOnClickListener {
                onAddToBasket(product)
            }
        }
    }
}