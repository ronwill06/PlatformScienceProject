package com.williams.platformscience

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.williams.platformscience.databinding.ActivityMainBinding
import com.williams.platformscience.domain.ShipmentsDriversResponse
import com.williams.platformscience.extensions.readAssetsFile
import kotlinx.serialization.json.Json

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}