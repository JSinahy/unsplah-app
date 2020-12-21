package com.laraguzman.tribalproofactivity.data.api

import com.laraguzman.tribalproofactivity.data.models.SearchPhotos
import com.laraguzman.tribalproofactivity.data.models.UnsplahPhotos
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashService {

    @GET("photos")
    fun searchPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Call<ArrayList<UnsplahPhotos>>

    @GET("search/photos")
    fun searchPhotos(
        @Query("client_id") clientId: String,
        @Query("query") criteria: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Call<SearchPhotos>

    @GET("users/{username}/photos")
    fun searchPhotoUsers(
        @Path("username") userName : String?,
        @Query("client_id") clientId: String

    ): Call<ArrayList<UnsplahPhotos>>

    @GET("photos/{id}")
    fun getPhotoDetail(
        @Path("id") id : String?,
        @Query("client_id") clientId: String

    ): Call<ArrayList<UnsplahPhotos>>

    @GET("users/{username}/collections")
    fun searchCollectionsUsers(
        @Path("username") userName : String?,
        @Query("client_id") clientId: String

    ): Call<ArrayList<UnsplahPhotos>>
}