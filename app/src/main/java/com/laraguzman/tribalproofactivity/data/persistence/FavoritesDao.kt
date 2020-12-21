package com.laraguzman.tribalproofactivity.data.persistence

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites")
    fun getAllFavorites() : LiveData<ArrayList<Favorites>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavorite(gavorite: Favorites)

    @Delete
    fun deleteFavorite(favorite: Favorites)
}