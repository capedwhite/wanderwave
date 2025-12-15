package com.example.wanderwave.viewmodel

import androidx.lifecycle.ViewModel
import com.example.wanderwave.model.ProductModel
import com.example.wanderwave.repository.ProductRepo

class ProductViewModel(val repo: ProductRepo): ViewModel() {


    fun addProduct(
        product: ProductModel,
        callback: (Boolean, String) -> Unit
    ){
repo.addProduct(product,callback)


    }

}