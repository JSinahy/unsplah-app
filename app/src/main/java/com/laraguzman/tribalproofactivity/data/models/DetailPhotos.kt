package com.laraguzman.tribalproofactivity.data.models

import java.io.Serializable

data class DetailPhotos(
    var id: String?,
    var created_at: String?,
    var updated_at: String?,
    var promoted_at: String?,
    var width: Int?,
    var height: Int?,
    var color: String?,
    var blur_hash: String?,
    var description: String?,
    var alt_description: String?,
    var urls: URLS?,
    var links: LINKS?,
    var categories: ArrayList<String>?,
    var likes: Int?,
    var liked_by_user: Boolean?,
    var current_user_collections: ArrayList<String>?,
    var sponsorship: SPONSORSHIP?,
    var user: USER,
    var exif: EXIF,
    var meta: META


    ) : Serializable
data class EXIF(
    var make: String,
    var model: String,
    var exposure_time: String,
    var aperture: String,
    var focal_length: String,
    var iso: Int
)

data class LOCATION(
    var title: String?,
    var name: String?,
    var city: String?,
    var country: String?,
    var position: POSITION
)

data class POSITION(
    val latitude: String?,
    var longitude: String?
)
data class META(
    var index: Boolean?
)

