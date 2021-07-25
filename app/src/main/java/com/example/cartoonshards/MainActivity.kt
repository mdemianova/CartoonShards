package com.example.cartoonshards

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.cartoonshards.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setListener()
    }

//    private fun setListener() {
//        val shards: List<View> = listOf(binding.view1, binding.view2, binding.view3, binding.view4,
//            binding.view5, binding.view6, binding.view7, binding.view8, binding.view9)
//
//        for (item in shards) {
//
//            item.setOnClickListener { it.visibility = View.INVISIBLE }
//        }
//    }

}