package com.psm.pinnedrv.sample

import com.psm.pinnedrv.Child

data class Product(
    val productId: Int,
    val name: String,
    val cover: String,
    val price: Float
): Child()