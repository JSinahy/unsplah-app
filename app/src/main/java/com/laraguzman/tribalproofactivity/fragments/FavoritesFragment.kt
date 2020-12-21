package com.laraguzman.tribalproofactivity.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.laraguzman.tribalproofactivity.R
import com.laraguzman.tribalproofactivity.databinding.FragmentFavoritesBinding
import com.laraguzman.tribalproofactivity.databinding.FragmentHomeBinding
import com.laraguzman.tribalproofactivity.ui.main.viewmodels.FavoritesFragmentViewModel
import com.laraguzman.tribalproofactivity.ui.main.viewmodels.HomeFragmentViewModel
import com.laraguzman.tribalproofactivity.utils.SpacesItemDecoration

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var binding : FragmentFavoritesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favorites, container, false)
        val viewModel = makeApiCall()

        binding?.viewModelFavorites = viewModel

        return binding?.root
    }

    fun makeApiCall() : FavoritesFragmentViewModel {
        val viewModel = ViewModelProvider(this).get(FavoritesFragmentViewModel::class.java)
        binding?.viewModelFavorites = viewModel

        val decoration = SpacesItemDecoration(32)
        StaggeredGridLayoutManager(2,
            StaggeredGridLayoutManager.VERTICAL
        ).apply {
            binding?.favoritesRecycler?.layoutManager = this

        }.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        binding?.favoritesRecycler?.addItemDecoration(decoration)

        val context = activity
        viewModel.GetListUnsplashPhotosFavorites().observe(viewLifecycleOwner, Observer { photos->
            if(photos != null){
                viewModel.SetAdapter(photos)

            }else{

                Toast.makeText(context, "Error in fetching data", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.GetDataFavorites()
        return viewModel
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}