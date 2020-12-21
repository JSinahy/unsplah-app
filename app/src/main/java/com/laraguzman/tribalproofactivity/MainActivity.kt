package com.laraguzman.tribalproofactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.shape.MaterialShapeDrawable
import com.laraguzman.tribalproofactivity.databinding.ActivityMainBinding
import com.laraguzman.tribalproofactivity.fragments.FavoritesFragment
import com.laraguzman.tribalproofactivity.fragments.HomeFragment
import com.laraguzman.tribalproofactivity.utils.BottomAppBarCutCornersTopEdge

class MainActivity : AppCompatActivity() {
    var binding : ActivityMainBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding?.bottomAppBar

        // Seteo el NavController
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragHost) as NavHostFragment
        val navController = navHostFragment.navController

        binding?.secondMenuItem?.setOnClickListener { view ->
            replaceFragment(FavoritesFragment())
        }

        binding?.firstMenuItem?.setOnClickListener { view ->
            replaceFragment(HomeFragment())
        }

    }

    fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragHost, fragment)
        fragmentTransaction.commit()
    }
}


