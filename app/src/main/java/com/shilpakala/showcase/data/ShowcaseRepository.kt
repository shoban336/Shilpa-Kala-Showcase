package com.shilpakala.showcase.data

class ShowcaseRepository {
    val sellers = listOf(
        Seller(
            uid = "seller-001",
            name = "Raghavendra Shilpi",
            village = "Shivarapatna",
            specialty = "Hoysala temple panels",
            bio = "Third-generation stone carver working in black granite and soapstone.",
            whatsappNumber = "919876543210",
            isVerifiedArtisan = true,
            rating = 4.9f,
            yearsOfExperience = 28,
            carvingStyles = listOf("Hoysala", "Dravidian", "Traditional")
        ),
        Seller(
            uid = "seller-002",
            name = "Meenakshi Wood Arts",
            village = "Kolar",
            specialty = "Wooden deity sculptures",
            bio = "A family studio known for expressive hand-carved teak and rosewood icons.",
            whatsappNumber = "919812345670",
            isVerifiedArtisan = true,
            rating = 4.8f,
            yearsOfExperience = 19,
            carvingStyles = listOf("Chola", "Wood", "Modern")
        )
    )

    val products = listOf(
        Product(
            productId = "SKS-2026-00001",
            sellerId = "seller-001",
            title = "Hoysala Lakshmi Panel",
            description = "A high-relief black granite panel inspired by Hoysala temple ornamentation, finished for indoor heritage walls.",
            material = "Black Granite",
            carvingStyle = "Hoysala",
            price = 185000,
            availability = "Available",
            stoneFreshness = "Fresh",
            dimensions = "36 x 24 x 5 in",
            weight = "92 kg",
            rating = 4.9f,
            viewCount = 1240
        ),
        Product(
            productId = "SKS-2026-00002",
            sellerId = "seller-001",
            title = "Dravidian Nandi Sculpture",
            description = "Commission-ready Nandi with crisp temple proportions, polished horns, and antique finish detailing.",
            material = "Sandstone",
            carvingStyle = "Dravidian",
            price = 260000,
            availability = "On Order",
            stoneFreshness = "Antique Finish",
            dimensions = "48 x 30 x 28 in",
            weight = "210 kg",
            rating = 4.7f,
            viewCount = 918
        ),
        Product(
            productId = "SKS-2026-00003",
            sellerId = "seller-002",
            title = "Teak Saraswati Idol",
            description = "Hand-carved teak Saraswati for home shrines and collectors, sealed with natural oil finish.",
            material = "Wood",
            carvingStyle = "Chola",
            price = 74000,
            availability = "Available",
            stoneFreshness = "Aged",
            dimensions = "22 x 12 x 8 in",
            weight = "9 kg",
            rating = 4.8f,
            viewCount = 681
        ),
        Product(
            productId = "SKS-2026-00004",
            sellerId = "seller-001",
            title = "Modern Garden Ganesha",
            description = "Minimal granite Ganesha designed for courtyards, resorts, and garden temples.",
            material = "Marble",
            carvingStyle = "Modern",
            price = 132000,
            availability = "Available",
            stoneFreshness = "Fresh",
            dimensions = "30 x 20 x 16 in",
            weight = "74 kg",
            rating = 4.6f,
            viewCount = 522
        )
    )

    val stories = listOf(
        HeritageStory("story-hoysala", "The Language of Hoysala Detail", "Hoysala", "Dense ornament, star-shaped temple plans, and patient micro-carving define this tradition."),
        HeritageStory("story-dravidian", "Dravidian Proportion and Presence", "Dravidian", "Balanced forms, sacred geometry, and durable temple sculpture practices.")
    )

    fun sellerFor(product: Product): Seller = sellers.first { it.uid == product.sellerId }
}
