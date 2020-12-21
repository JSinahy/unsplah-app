package com.laraguzman.tribalproofactivity.data.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(entities = arrayOf(Favorites::class), version = 1, exportSchema = false)
//abstract class FavoritesDatabase : RoomDatabase() {
//
//    companion object {
//        @Volatile
//        private var INSTANCE: FavoritesDatabase? = null
//        fun getInstance(context: Context) : FavoritesDatabase {
//            return INSTANCE ?: synchronized(this){
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    FavoritesDatabase::class.java,
//                    "favorites.db"
//                ).build()
//                INSTANCE = instance
//                instance
//            }
//        }
//    }
//
//}