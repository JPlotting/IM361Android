package com.jakobplotts.allnews

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView


// Images taken royalty free from: https://www.pexels.com/

class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        // Grab references to each thumbnail in the gallery
        val imageGalleryThumb1: ImageView = findViewById(R.id.ImageGalleryThumb1)
        val imageGalleryThumb2: ImageView = findViewById(R.id.ImageGalleryThumb2)
        val imageGalleryThumb3: ImageView = findViewById(R.id.ImageGalleryThumb3)

        // Make the first thumbnail tappable so it goes to the article
        imageGalleryThumb1.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this,imageGalleryThumb1,"ImageGalleryThumb1")
            startActivity(intent,options.toBundle())
        }

        // Make the second thumbnail tappable so it goes to the article
        imageGalleryThumb2.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this,imageGalleryThumb2,"ImageGalleryThumb2")
            startActivity(intent,options.toBundle())
        }

        // Make the third thumbnail tappable so it goes to the article
        imageGalleryThumb3.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this,imageGalleryThumb3,"ImageGalleryThumb3")
            startActivity(intent,options.toBundle())
        }
    }
}