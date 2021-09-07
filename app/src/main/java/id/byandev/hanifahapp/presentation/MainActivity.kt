package id.byandev.hanifahapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint
import id.byandev.hanifahapp.R

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

/*
base url
https://islamic-api-indonesia.herokuapp.com
https://masjid-api-indonesia.herokuapp.com

 */