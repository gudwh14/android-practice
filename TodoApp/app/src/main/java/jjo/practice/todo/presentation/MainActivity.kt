package jjo.practice.todo.presentation

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import jjo.practice.todo.R
import jjo.practice.todo.databinding.ActivityMainBinding
import jjo.practice.todo.presentation.home.HomeFragment
import jjo.practice.todo.presentation.todo.TodoFragment
import java.time.Instant
import kotlin.math.log
import kotlin.math.log10

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var navController : NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // FragmentContainerView 를 이용해 NavHostFragment 를 만들때는 NavHostFragment 에서 NavController 를 찾아야 한다.
        val navHostFragment = supportFragmentManager.findFragmentById(binding.navHostFragment.id) as NavHostFragment
        val navController = navHostFragment.navController

        binding!!.bottomNavigationView.setOnItemSelectedListener {
            val currentFragment = navHostFragment.childFragmentManager.fragments[0]

            when(it.itemId) {
                R.id.action_home -> {
                    // is 문법으로 객채타입 확인
                    if(currentFragment is TodoFragment) {
                        navController.navigate(R.id.action_todoFragment_to_homeFragment)
                        return@setOnItemSelectedListener true
                    }
                }
                R.id.action_todo -> {
                    if(currentFragment is HomeFragment) {
                        navController.navigate(R.id.action_homeFragment_to_todoFragment)
                        return@setOnItemSelectedListener true
                    }
                }
            }
            false
        }

    }

}