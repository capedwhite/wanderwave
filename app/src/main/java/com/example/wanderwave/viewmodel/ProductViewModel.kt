package com.example.wanderwave.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.wanderwave.model.ProductModel
import com.example.wanderwave.repository.ProductRepo

class ProductViewModel(private val repo: ProductRepo) : ViewModel() {

    private val _allProducts = MutableLiveData<List<ProductModel>>()
    val allProducts: LiveData<List<ProductModel>> get() = _allProducts

    private val _selectedProduct = MutableLiveData<ProductModel?>()
    val selectedProduct: LiveData<ProductModel?> get() = _selectedProduct

    fun addProduct(
        product: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        repo.addProduct(product, callback)
    }

    fun getAllProducts() {
        repo.getAllProducts { success, _, data ->
            _allProducts.value = if (success) data ?: emptyList() else emptyList()
        }
    }

    fun getProductById(productId: String) {
        repo.getProductById(productId) { success, _, product ->
            _selectedProduct.value = if (success) product else null
        }
    }

    fun updateProduct(
        product: ProductModel,
        callback: (Boolean, String) -> Unit
    ) {
        repo.updateProduct(product, callback)
    }

    fun deleteProduct(
        productId: String,
        callback: (Boolean, String) -> Unit
    ) {
        repo.deleteProduct(productId, callback)
    }
    fun uploadImage(
        context: Context,
        imageUri: Uri,
        callback: (String?) -> Unit

    ){
        repo.uploadImage(context,imageUri,callback)
    }
}
