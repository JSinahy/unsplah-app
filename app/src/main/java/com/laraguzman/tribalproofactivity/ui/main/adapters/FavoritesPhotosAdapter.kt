package com.laraguzman.tribalproofactivity.ui.main.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.laraguzman.tribalproofactivity.data.models.UnsplahPhotos
import com.laraguzman.tribalproofactivity.databinding.ItemFavoritePhotosBinding
import com.laraguzman.tribalproofactivity.databinding.ItemPhotoUnsplashBinding

class FavoritesPhotosAdapter(dataListener: FavoritesPhotosListener) : RecyclerView.Adapter<FavoritesPhotosAdapter.MyViewHolder>(){
    var items = ArrayList<UnsplahPhotos>()
    var parentContext : Context? = null

    var unsplashListener: FavoritesPhotosListener? = dataListener

    fun setDataList(data: ArrayList<UnsplahPhotos>){
        this.items = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesPhotosAdapter.MyViewHolder {
        parentContext = parent.context
        val layout = LayoutInflater.from(parent.context)
        val binding = ItemFavoritePhotosBinding.inflate(layout)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavoritesPhotosAdapter.MyViewHolder, position: Int) {
        val unsplash : UnsplahPhotos = items[position]
        holder.itemBinding.itemCard.setOnClickListener {
            unsplashListener?.onClickPhoto(unsplash, 1) // Detail Photo
        }
        holder.itemBinding.cardImageProfile.setOnClickListener {
            unsplashListener?.onClickPhoto(unsplash, 2) // Profile
        }
        holder.itemBinding.itemFavorite.setOnClickListener {
            unsplashListener?.onClickPhoto(unsplash, 3) // Profile
        }
        holder.bind(unsplash)
    }

    override fun getItemCount() = items.size



    class MyViewHolder(val binding: ItemFavoritePhotosBinding) : RecyclerView.ViewHolder(binding.root){

        var itemBinding : ItemFavoritePhotosBinding = binding

        fun bind(data: UnsplahPhotos){
            binding.itemRecyclerPhoto = data
            binding.executePendingBindings()
            binding.itemLikes.text = "Likes " + data.likes.toString()

            val circularProgress = binding.itemProgressBar

            Glide.with(binding.imageItem)
                .load(data.urls?.full)
                .listener(object: RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.itemProgressBar.visibility = View.GONE
                        return false;
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.itemProgressBar.visibility = View.GONE
                        return false;
                    }

                })
                .into(binding.imageItem)

            Glide.with(binding.imageProfile)
                .load(data.user.profile_image.medium)
                .into(binding.imageProfile)

        }
    }

}