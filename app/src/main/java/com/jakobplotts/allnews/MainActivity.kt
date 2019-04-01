package com.jakobplotts.allnews

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.widget.Button
import android.widget.TextView
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    override fun onMapReady(map: GoogleMap?) {
        val morton = LatLng(40.6091693,-89.5392298)
        map?.addMarker(MarkerOptions().position(morton).title("Pumpkin capital of the world"))
        map?.moveCamera(CameraUpdateFactory.newLatLng(morton))
    }

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myText: TextView = findViewById(R.id.my_text_view)
        val myButton: Button = findViewById(R.id.my_button)
        val myButtonViewTheirContent: Button = findViewById(R.id.button_view_their_content)
        val anim = SpringAnimation(myText,DynamicAnimation.TRANSLATION_Y,-48f)
        anim.setStartValue(0f)

        myButton.setOnClickListener {
            counter++
            myText.text = resources.getQuantityString(R.plurals.textView_text,counter,counter)
            anim.start()
        }

        myButtonViewTheirContent.setOnClickListener {
            val intent = Intent(this,GalleryActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this,myText,"image_gallery_example")
            startActivity(intent,options.toBundle())
        }

        val mapFragment = supportFragmentManager.findFragmentById(R.id.my_map_view) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
