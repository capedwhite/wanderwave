package com.example.wanderwave.model

data class ProductModel(
    val ProductName:String="",
    var ProductId:String="",
    val ProductDescription:String="",
    val ProductPrice:String="")
{
    fun toMap(): Map<String , Any?>{
        return mapOf(
            "ProductName" to ProductName,
            "ProductId" to ProductId,
            "ProductDescription" to ProductDescription,
            "ProductPrice" to ProductPrice
        )
    }

}

