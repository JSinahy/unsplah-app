package com.laraguzman.tribalproofactivity.ui.main.viewmodels

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.laraguzman.tribalproofactivity.data.api.UnsplashApinstance
import com.laraguzman.tribalproofactivity.data.api.UnsplashService
import com.laraguzman.tribalproofactivity.data.models.UnsplahPhotos
import com.laraguzman.tribalproofactivity.ui.main.adapters.HomePhotosAdapter
import com.laraguzman.tribalproofactivity.ui.main.adapters.ProfileActivityAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel : ViewModel() {
    lateinit var unsplashProfilePhotos : MutableLiveData<ArrayList<UnsplahPhotos>>
    lateinit var unsplashProfileAdapter : ProfileActivityAdapter

    init{
        unsplashProfilePhotos = MutableLiveData()
        unsplashProfileAdapter = ProfileActivityAdapter()
    }

    fun GetAdapter() : ProfileActivityAdapter {
        return unsplashProfileAdapter
    }

    @SuppressLint("NotifyDataSetChanged")
    fun SetAdapter(data: ArrayList<UnsplahPhotos>){
        unsplashProfileAdapter.setDataList(data)
        unsplashProfileAdapter.notifyDataSetChanged()
    }

    fun GetListUnsplashPhotos() : MutableLiveData<ArrayList<UnsplahPhotos>>{
        return unsplashProfilePhotos
    }

    fun GetPhotosFromApi(username: String?){
        val retrofitInstance = UnsplashApinstance().GetInstance().create(UnsplashService::class.java)
        val call = retrofitInstance.searchPhotoUsers(username, "<YOUR API KEY>")
        call.enqueue(object : Callback<ArrayList<UnsplahPhotos>> {
            override fun onResponse(
                call: Call<ArrayList<UnsplahPhotos>>,
                response: Response<ArrayList<UnsplahPhotos>>
            ) {
                if (response.isSuccessful) {
                    unsplashProfilePhotos.postValue(response.body())
                } else {
                    unsplashProfilePhotos.postValue(null)
                }
            }

            override fun onFailure(call: Call<ArrayList<UnsplahPhotos>>, t: Throwable) {
                Log.wtf("DEVOLUCION", t.message)
                unsplashProfilePhotos.postValue(null)
            }

        })
    }
}
