package com.example.wanderwave.repository

import com.example.wanderwave.model.ProductModel
import javax.security.auth.callback.Callback

interface ProductRepo {
    fun addProduct(
        product: ProductModel,
        callback: (Boolean, String) -> Unit
    )

    fun getAllProducts(callback: (Boolean, String, List<ProductModel>?) -> Unit)

    fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    )

    fun updateProduct(
        productId: String,
        product: ProductModel,
        callback: (Boolean, String) -> Unit
    )

    fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    )

}