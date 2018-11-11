package com.example.peter.kpropertiesmvvm.repository.model

/**
 * This is the raw JSON objects that are received from network call.
 */

object Model {

    data class Result(val data: Data)

    data class Data(val listings: List<Listings>)

    data class Listings(val Id: String, val Area: String, val Bathrooms: Int, val Bedrooms: Int, val Carspaces: Int,
                        val Description: String, val is_premium: Int, val owner: Owner, val DisplayPrice: String,
                        val Location: Location
    )

    data class Owner (val name: String, val lastName: String, val image: Image)

    data class Image (val medium: Medium)

    data class Medium (val url: String)

    data class Location (val Address: String, val Address2: String, val Suburb: String, val State: String)

}