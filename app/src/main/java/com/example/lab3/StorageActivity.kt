package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import com.example.lab3.databinding.ActivityMainBinding
import com.example.lab3.databinding.ActivityStorageBinding
import java.io.File

class StorageActivity : AppCompatActivity() {

    lateinit var binding : ActivityStorageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val file = File(filesDir, "storage.txt")
        val contents = file.readText()

        when (contents.length) {
            0 -> binding.textView.text = "Storage is empty"
            else -> binding.textView.text = contents
        }

        binding.textView.movementMethod = ScrollingMovementMethod()
    }
}