package com.laraguzman.tribalproofactivity.ui.main.viewmodels

import android.app.Application
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.laraguzman.tribalproofactivity.DetailPhotoActivity
import com.laraguzman.tribalproofactivity.ProfileActivity
import com.laraguzman.tribalproofactivity.data.models.UnsplahPhotos
import com.laraguzman.tribalproofactivity.data.persistence.Preferences
import com.laraguzman.tribalproofactivity.ui.main.adapters.FavoritesPhotosAdapter
import com.laraguzman.tribalproofactivity.ui.main.adapters.FavoritesPhotosListener
import com.laraguzman.tribalproofactivity.ui.main.adapters.HomePhotosAdapter
import java.io.Serializable


class FavoritesFragmentViewModel(val app: Application) : AndroidViewModel(app), FavoritesPhotosListener {
    var unsplashFavorites: MutableLiveData<ArrayList<UnsplahPhotos>>
    var modelo : ArrayList<UnsplahPhotos>? = null
    var unsplashAdapter : FavoritesPhotosAdapter

    companion object {
        lateinit var prefs: Preferences
    }

    init{
        unsplashFavorites = MutableLiveData()
        prefs = Preferences(app.applicationContext)
        modelo = ArrayList()
        unsplashAdapter = FavoritesPhotosAdapter(this)
    }

    fun GetAdapter() : FavoritesPhotosAdapter{
        return unsplashAdapter
    }

    fun SetAdapter(data: ArrayList<UnsplahPhotos>){
        unsplashAdapter.setDataList(data)
        unsplashAdapter.notifyDataSetChanged()
    }

    fun GetListUnsplashPhotosFavorites() : MutableLiveData<ArrayList<UnsplahPhotos>>{
        return unsplashFavorites
    }

    fun GetDataFavorites(){
        //Log.wtf("CADENA", prefs.name)
        val listType = object : TypeToken<ArrayList<UnsplahPhotos?>?>() {}.type
        modelo = Gson().fromJson(prefs.name, listType)
        unsplashFavorites.postValue(modelo)
    }

    override fun onClickPhoto(data: UnsplahPhotos, action: Int) {
        when(action) {
            2-> {
                Toast.makeText(app.applicationContext, data.user?.username, Toast.LENGTH_LONG).show()

                val intent = Intent(app, ProfileActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("SALUDO", "QUE PEX MOTHER FUCKER")
                intent.putExtra("PROFILE", data as Serializable)
                app.startActivity(intent)
                //Log.wtf("PHOTO", data.user?.username)
            }
            1-> {
                Toast.makeText(app.applicationContext, data.user?.username, Toast.LENGTH_LONG).show()

                val intent = Intent(app, DetailPhotoActivity::class.java)
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.putExtra("SALUDO", "QUE PEX MOTHER FUCKER")
                intent.putExtra("PROFILE", data as Serializable)
                app.startActivity(intent)
                //Log.wtf("PHOTO", data.user?.username)
            }
            3-> {
                val listType = object : TypeToken<ArrayList<UnsplahPhotos?>?>() {}.type
                modelo = Gson().fromJson(prefs.name, listType)
                modelo?.remove(data)
                val datos : String = Gson().toJson(modelo)
                prefs.name = datos
                unsplashFavorites.postValue(modelo)
                // Para eliminar un elemento de mis favoritos
            }
        }
    }
}