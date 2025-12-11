package com.example.wanderwave.repository

import com.example.wanderwave.model.ProductModel
import javax.security.auth.callback.Callback

interface ProductRepo {
    fun addProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String)-> Unit
    )
    fun updateProduct(
        productId:String,
        model: ProductModel,
        callback:(Boolean,String)->Unit
    )
    fun getProduct(
        productId: String,
        callback: (Boolean, String, ProductModel) -> Unit

    )
    fun getAllProducts(
        productId: String,
        callback:(Boolean,String,List<ProductModel>)->Unit
    )

}