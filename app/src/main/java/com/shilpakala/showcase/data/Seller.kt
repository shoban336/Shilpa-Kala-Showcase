package com.shilpakala.showcase.data

data class Seller(
    val uid: String,
    val name: String,
    val village: String,
    val specialty: String,
    val bio: String,
    val whatsappNumber: String,
    val isVerifiedArtisan: Boolean,
    val rating: Float,
    val yearsOfExperience: Int,
    val carvingStyles: List<String>
)
