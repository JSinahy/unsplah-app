package com.laraguzman.tribalproofactivity.data.models

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseMethod
import java.io.Serializable

data class UnsplahPhotos(
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

) : Serializable
data class USER(
                var id: String?,
                var updated_at: String?,
                var username: String?,
                var name: String?,
                var first_name: String?,
                var last_name: String?,
                var twitter_username: String?,
                var portofolio_url: String?,
                var bio: String?,
                var location: String?,
                var links: SPONSORLINKS,
                var profile_image: PROFILEIMAGE,
                var instagram_username: String?,
                var total_collections: Int?,
                var total_likes: Int?,
                var total_photos: Int?,
                var accepted_tos: Boolean?
) : Serializable
data class URLS(
                var raw: String?,
                var full: String?,
                var regular: String?,
                var small: String?,
                var thumb: String?
): Serializable

data class LINKS(
                var self: String?,
                var html: String?,
                var download: String?,
                var download_location: String?
): Serializable

data class SPONSORSHIP(
                var impression_url: ArrayList<String>?,
                var tagline: String?,
                var tagline_url: String?,
                var sponsor: SPONSOR
): Serializable

data class SPONSOR(
                var id: String?,
                var updated_at: String?,
                var username: String?,
                var name: String?,
                var first_name: String?,
                var lastname_name: String?,
                var twitter_username: String?,
                var portofolio_url: String?,
                var bio: String?,
                var location: String?,
                var links: SPONSORLINKS,
                var profile_image: PROFILEIMAGE,
                var instagram_username: String?,
                var total_collections: Int?,
                var total_likes: Int?,
                var total_photos: Int?,
                var accepted_tos: Boolean?
): Serializable

data class PROFILEIMAGE(
                var small: String?,
                var medium: String?,
                var large: String?
): Serializable

data class SPONSORLINKS(
                var self: String?,
                var html: String?,
                var photos: String?,
                var likes: String?,
                var portofolio: String?,
                var following: String?,
                var followers: String?
): Serializable

