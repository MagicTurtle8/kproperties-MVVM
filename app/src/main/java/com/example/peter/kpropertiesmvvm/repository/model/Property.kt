package com.example.peter.kpropertiesmvvm.repository.model

/**
 *  Property data class
 */

data class Property
    (val id: String, val area: String, val bedrooms: Int, val bathrooms: Int, val carSpace: Int,
     val description: String, val displayPrice: String, val imageUrl: String, val address1: String, val address2: String,
     val suburb: String, val state: String, val premium: Boolean)

