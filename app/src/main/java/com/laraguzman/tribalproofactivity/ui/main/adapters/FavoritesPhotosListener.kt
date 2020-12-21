package com.laraguzman.tribalproofactivity.ui.main.adapters

import com.laraguzman.tribalproofactivity.data.models.UnsplahPhotos

interface FavoritesPhotosListener {
    fun onClickPhoto(data: UnsplahPhotos, action: Int)
}