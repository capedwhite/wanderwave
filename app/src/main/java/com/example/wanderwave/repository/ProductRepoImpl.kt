package com.example.wanderwave.repository

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.OpenableColumns
import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import com.example.wanderwave.model.ProductModel
import com.google.firebase.database.*
import java.io.InputStream
import java.util.concurrent.Executors

class ProductRepoImpl : ProductRepo {

    private val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val ref: DatabaseReference = database.getReference("products")
    private val cloudinary = Cloudinary(
        mapOf(
            "cloud_name" to "dpqtxhazp",
            "api_key" to "688822756376519",
            "api_secret" to "c8iQSprqFFIJzY6A3E944iO9Y50"
        )
    )

    override fun uploadImage(context: Context, imageUri: Uri, callback: (String?) -> Unit) {
        val executor = Executors.newSingleThreadExecutor()
        executor.execute {
            try {
                val inputStream: InputStream? = context.contentResolver.openInputStream(imageUri)
                var fileName = getFileNameFromUri(context, imageUri)

                fileName = fileName?.substringBeforeLast(".") ?: "uploaded_image"

                val response = cloudinary.uploader().upload(
                    inputStream, ObjectUtils.asMap(
                        "public_id", fileName,
                        "resource_type", "image"
                    )
                )

                var imageUrl = response["url"] as String?

                imageUrl = imageUrl?.replace("http://", "https://")

                Handler(Looper.getMainLooper()).post {
                    callback(imageUrl)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                Handler(Looper.getMainLooper()).post {
                    callback(null)
                }
            }
        }
    }
    override fun getFileNameFromUri (
        context: Context,
        imageUri: Uri
    ): String? {
        var fileName: String? = null
        val cursor: Cursor? = context.contentResolver.query(imageUri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                if (nameIndex != -1) {
                    fileName = it.getString(nameIndex)
                }
            }
        }
        return fileName
    }
    override fun addProduct(
        product: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        val productId = ref.push().key ?: return
        product.productId = productId

        ref.child(productId)
            .setValue(product)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Product added successfully")
                } else {
                    callback(false, it.exception?.message ?: "Unknown error")
                }
            }
    }

    override fun getAllProducts(
        callback: (Boolean, String, List<ProductModel>?) -> Unit
    ) {
        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                if (!snapshot.exists()) {
                    callback(false, "No products found", emptyList())
                    return
                }

                val productList = mutableListOf<ProductModel>()
                for (child in snapshot.children) {
                    val product = child.getValue(ProductModel::class.java)
                    product?.let { productList.add(it) }
                }

                callback(true, "Products fetched", productList)
            }

            override fun onCancelled(error: DatabaseError) {
                callback(false, error.message, null)
            }
        })
    }

    override fun getProductById(
        productId: String,
        callback: (Boolean, String, ProductModel?) -> Unit
    ) {
        ref.child(productId)
            .addListenerForSingleValueEvent(object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {
                    if (!snapshot.exists()) {
                        callback(false, "Product not found", null)
                        return
                    }

                    val product = snapshot.getValue(ProductModel::class.java)
                    callback(true, "Product found", product)
                }

                override fun onCancelled(error: DatabaseError) {
                    callback(false, error.message, null)
                }
            })
    }

    override fun updateProduct(
        product: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(product.productId)
            .updateChildren(product.toMap())
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Product updated successfully")
                } else {
                    callback(false, it.exception?.message ?: "Update failed")
                }
            }
    }

    override fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(productId)
            .removeValue()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    callback(true, "Product deleted")
                } else {
                    callback(false, it.exception?.message ?: "Delete failed")
                }
            }
    }


}
