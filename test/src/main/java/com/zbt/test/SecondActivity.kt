package com.zbt.test

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.zbt.test.databinding.ActivityMainBinding

/**
 *Author: zbt
 *Time: 2021/5/9 18:07
 *Description: This is SecondActivity
 */
class SecondActivity :AppCompatActivity(){

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("SecondActivity onCreate()")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


    override fun onDestroy() {
        setResult(Activity.RESULT_OK)
        super.onDestroy()
    }
}