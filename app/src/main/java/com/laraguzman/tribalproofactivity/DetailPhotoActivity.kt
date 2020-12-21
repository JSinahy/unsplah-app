package com.laraguzman.tribalproofactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.laraguzman.tribalproofactivity.data.models.UnsplahPhotos
import com.laraguzman.tribalproofactivity.databinding.ActivityDetailPhotoBinding

class DetailPhotoActivity : AppCompatActivity() {

    var binding: ActivityDetailPhotoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_photo)

        val photo : UnsplahPhotos = intent.getSerializableExtra("PROFILE") as UnsplahPhotos
        binding = DataBindingUtil.setContentView(this, R.layout.activity_detail_photo)

        Glide.with(binding?.imageDetailPhoto!!)
            .load(photo.urls?.full)
            .into(binding?.imageDetailPhoto!!)
        binding?.textDetailUsername?.text = if(photo.user.first_name == null) "" else photo.user.first_name  + " " + if(photo.user.last_name == null)  "" else photo.user.last_name
        binding?.textDetailDescription?.text = if(photo.alt_description == null) "No description" else photo.alt_description
        binding?.textDetailCreated?.text = photo.created_at
    }
}