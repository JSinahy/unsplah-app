package com.laraguzman.tribalproofactivity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.laraguzman.tribalproofactivity.data.models.UnsplahPhotos
import com.laraguzman.tribalproofactivity.databinding.ActivityProfileBinding
import com.laraguzman.tribalproofactivity.ui.base.BaseApplication
import com.laraguzman.tribalproofactivity.ui.main.viewmodels.HomeFragmentViewModel
import com.laraguzman.tribalproofactivity.ui.main.viewmodels.ProfileViewModel
import com.laraguzman.tribalproofactivity.utils.SpacesItemDecoration

class ProfileActivity : BaseApplication() {

    var binding: ActivityProfileBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_profile)

        binding = DataBindingUtil.setContentView(instance, R.layout.activity_profile)

        val message = intent.getStringExtra("SALUDO")
        val profile : UnsplahPhotos = intent.getSerializableExtra("PROFILE") as UnsplahPhotos

        //Toast.makeText(this, profile.user?.name, Toast.LENGTH_LONG).show()

        SetupData(profile)
        val viewModel = makeApiCall(profile)
        binding?.viewModelProfile = viewModel
    }

    fun SetupData(profile: UnsplahPhotos){
        binding?.avatarUnsplash?.let {
            Glide.with(it)
                .load(profile.user.profile_image.large)
                .into(binding?.avatarUnsplash!!)
        }
        binding?.textName?.text         = if(profile.user.first_name == null) "" else profile.user.first_name  + " " + if(profile.user.last_name == null)  "" else profile.user.last_name
        binding?.totalPhotos?.text      = profile.user.total_photos.toString()
        binding?.totalCollections?.text = profile.user.total_collections.toString()
        binding?.totalLikes?.text       = profile.user.total_likes.toString()

        binding?.twitterIcon?.setOnClickListener {
            SendToURLBrowser("https://twitter.com/" + profile.user.twitter_username)
        }

        binding?.instagramIcon?.setOnClickListener {
            SendToURLBrowser("https://www.instagram.com/" + profile.user.twitter_username)
        }

        binding?.textLocation?.text = if(profile.user.location == null) "No data location" else profile.user.location
    }

    fun SendToURLBrowser(url: String){
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    fun makeApiCall(profile: UnsplahPhotos) : ProfileViewModel {
        val viewModel = ViewModelProvider(instance).get(ProfileViewModel::class.java)
        binding?.viewModelProfile = viewModel

        val decoration = SpacesItemDecoration(32)
        StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.HORIZONTAL
        ).apply {
            binding?.recyclerProfilePhotos?.layoutManager = this

        }.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        binding?.recyclerProfilePhotos?.addItemDecoration(decoration)


        viewModel.GetListUnsplashPhotos().observe(instance, Observer { photos->
            if(photos != null){
                viewModel.SetAdapter(photos)
            }else{
                Toast.makeText(instance, "Error in fetching data", Toast.LENGTH_LONG).show()
            }
        })
        val username: String? = profile.user.username
        viewModel.GetPhotosFromApi(username)
        return viewModel
    }
}