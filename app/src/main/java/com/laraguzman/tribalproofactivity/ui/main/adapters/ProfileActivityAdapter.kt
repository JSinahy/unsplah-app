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
import com.laraguzman.tribalproofactivity.databinding.ItemProfilePhotosBinding

class ProfileActivityAdapter() : RecyclerView.Adapter<ProfileActivityAdapter.MyViewHolder>(){
    var items = ArrayList<UnsplahPhotos>()
    var parentContext : Context? = null

    fun setDataList(data: ArrayList<UnsplahPhotos>){
        this.items = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileActivityAdapter.MyViewHolder {
        parentContext = parent.context
        val layout = LayoutInflater.from(parent.context)
        val binding = ItemProfilePhotosBinding.inflate(layout)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileActivityAdapter.MyViewHolder, position: Int) {
        val unsplash : UnsplahPhotos = items[position]
        holder.bind(unsplash)
    }

    override fun getItemCount() = items.size



    class MyViewHolder(val binding: ItemProfilePhotosBinding) : RecyclerView.ViewHolder(binding.root){
        var itemBinding : ItemProfilePhotosBinding = binding

        fun bind(data: UnsplahPhotos){
            //binding.itemRecyclerPhoto = data
            binding.executePendingBindings()

            //val circularProgress = binding.itemProgressBar

            Glide.with(binding.itemIMagePhoto)
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
                .into(binding.itemIMagePhoto)



        }
    }

}