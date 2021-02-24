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
import com.laraguzman.tribalproofactivity.databinding.FragmentHomeBinding
import com.laraguzman.tribalproofactivity.ui.main.viewmodels.HomeFragmentViewModel
import com.laraguzman.tribalproofactivity.utils.SpacesItemDecoration

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    var binding : FragmentHomeBinding? = null

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
        //viewModelFactory = Injection.provideViewModelFactory(requireActivity())

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val viewModel = makeApiCall()
        binding?.homeViewModel = viewModel


        binding?.searchButton?.setOnClickListener {
            viewModel.GetListUnsplashPhotos().observe(viewLifecycleOwner, Observer {
                if(it != null){
                    viewModel.SetAdapter(it)
                }else{
                    Toast.makeText(context, "Error in fetching data", Toast.LENGTH_LONG).show()
                }
            })
            viewModel.GetPhotosFromApiSearch(binding?.textSearch?.text.toString())
        }

        return binding?.root
    }

    fun makeApiCall() : HomeFragmentViewModel{
        val viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        binding?.homeViewModel = viewModel

        val decoration = SpacesItemDecoration(32)
        StaggeredGridLayoutManager(2,
        StaggeredGridLayoutManager.VERTICAL
        ).apply {
            binding?.recyclerHomePhotos?.layoutManager = this

        }.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS

        binding?.recyclerHomePhotos?.addItemDecoration(decoration)

        viewModel?.searchText.observe( viewLifecycleOwner, Observer {
            Log.wtf("BUSQUEDA", it)
        })
        val context = activity
        viewModel.GetListUnsplashPhotos().observe(viewLifecycleOwner, Observer { photos->
            if(photos != null){
                if(photos.isEmpty())
                    binding?.groupEmpyState?.visibility = View.VISIBLE
                else{
                    binding?.groupEmpyState?.visibility = View.GONE
                    viewModel.SetAdapter(photos)
                }

            }else{

                Toast.makeText(context, "Error in fetching data", Toast.LENGTH_LONG).show()
            }
        })
        viewModel.GetPhotosFromApi()
        return viewModel
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}