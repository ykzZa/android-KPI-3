package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import androidx.activity.viewModels
import com.example.lab3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutInput, DataInputFragment.newInstance())
            .commit()

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayoutConfirm, NotificationFragment.newInstance())
            .commit()
    }
}