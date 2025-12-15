package com.example.wanderwave.repository

import com.example.wanderwave.model.ProductModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ProductRepoImpl: ProductRepo {
    val database : FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref : DatabaseReference = database.getReference("products")
    override fun addProduct(
        product: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        val productId = ref.push().key.toString()
        product.ProductId = productId

        ref.child(productId).setValue(product).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "product added sucessfully")
            } else {
                callback(false, "${it.exception?.message}")
            }
        }
    }

    override fun getAllProducts(callback: (Boolean, String, List<ProductModel>?) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun updateProduct(
        productId: String,
        product: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}

