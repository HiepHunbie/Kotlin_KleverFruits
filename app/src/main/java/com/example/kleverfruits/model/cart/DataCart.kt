package com.example.kleverfruits.model.cart

import com.example.kleverfruits.model.cart.cartDetail.DataCartDetail

data class DataCart (
    val type_pay : Int,
    val total_product_cost : String,
    val shipping_cost : String,
    val sale_code : String,
    val total_cost : String,
    val listCartDetail : List<DataCartDetail>
)