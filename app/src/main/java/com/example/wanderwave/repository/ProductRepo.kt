package com.example.wanderwave.repository

import android.content.Context
import android.net.Uri
import com.example.wanderwave.model.ProductModel

interface ProductRepo {

    fun addProduct(
        product: ProductModel,
        callback: (Boolean, String) -> Unit
    )

    fun getAllProducts(
        callback: (Boolean, String, List<ProductModel>?) -> Unit
    )

    fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    )

    fun updateProduct(
        product: ProductModel,
        callback: (Boolean, String) -> Unit
    )

    fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    )
    fun uploadImage(
        context: Context,
        imageUri: Uri,
        callback: (String?) -> Unit

        )
    fun getFileNameFromUri(
        context: Context,
        imageUri: Uri
    ):String?
}
