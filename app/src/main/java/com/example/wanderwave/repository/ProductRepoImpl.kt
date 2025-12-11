package com.example.wanderwave.repository

import com.example.wanderwave.model.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProductRepoImpl: ProductRepo {
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref : DatabaseReference = database.getReference("users")

    override fun addProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        var id=ref.push().key.toString()

    }

    override fun updateProduct(
        productId: String,
        model: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getProduct(
        productId: String,
        callback: (Boolean, String, ProductModel) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getAllProducts(
        productId: String,
        callback: (Boolean, String, List<ProductModel>) -> Unit
    ) {
        TODO("Not yet implemented")
    }


}