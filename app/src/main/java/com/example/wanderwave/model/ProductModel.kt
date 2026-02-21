package com.example.wanderwave.model

data class ProductModel(
    var productId: String = "",
    var productName: String = "",
    var productDescription: String = "",
    var productPrice: String = "",
    var productTime: String = "",
    var productImage:String=""
) {
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "productId" to productId,
            "productName" to productName,
            "productDescription" to productDescription,
            "productPrice" to productPrice,
            "productTime" to productTime,
            "productImage" to productImage
        )
    }
}

