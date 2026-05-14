package com.shilpakala.showcase.data

data class Product(
    val productId: String,
    val sellerId: String,
    val title: String,
    val description: String,
    val material: String,
    val carvingStyle: String,
    val price: Long,
    val availability: String,
    val stoneFreshness: String,
    val dimensions: String,
    val weight: String,
    val rating: Float,
    val viewCount: Int,
    val imageUrl: Any = "",
    val wipTimelineImages: List<Any> = emptyList()
)
