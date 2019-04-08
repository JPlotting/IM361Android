package com.jakobplotts.allnews

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.SpringAnimation
import android.support.animation.SpringForce
import android.support.design.widget.FloatingActionButton
import android.widget.Button
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem


class MainActivity : AppCompatActivity() {
    // Current state of the fav action button
    private var articleFaved = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // App bar setup with help from: https://developer.android.com/training/appbar/setting-up
        setSupportActionBar(findViewById(R.id.ToolbarArticle))

        // Grab element references
        val floatingFavButton: FloatingActionButton = findViewById(R.id.FloatingFavButton)
        val buttonViewMore: Button = findViewById(R.id.ButtonViewMore)

        // Create a custom spring animation to cause a hop animation for pressing the fav button
        // Assistance/code from: https://code.tutsplus.com/tutorials/adding-physics-based-animations-to-android-apps--cms-29053
        val favSpring: SpringForce = SpringForce()
        favSpring.setDampingRatio(SpringForce.DAMPING_RATIO_HIGH_BOUNCY)
        favSpring.setStiffness(SpringForce.STIFFNESS_LOW)

        // Attaching custom spring to animation
        val favAnim = SpringAnimation(floatingFavButton, DynamicAnimation.Y)
        favAnim.setSpring(favSpring)

        // Set a listener on the "view more" button so that it will take the user to the gallery activity
        // Transition from in class material
        buttonViewMore.setOnClickListener {
            val intent = Intent(this,GalleryActivity::class.java)
            val options = ActivityOptions.makeSceneTransitionAnimation(this,buttonViewMore,"ImageGalleryThumb1")
            startActivity(intent,options.toBundle())
        }

        // Animated fav button upon tapping (it toggles states but that is it)
        // Physics animations according to course material
        floatingFavButton.setOnClickListener {
            // Make sure to only allow tapping when not being animated (no physics stacking!)
            if (!favAnim.isRunning) {
                // Toggle the state of the fav button and alter the tint to reflect it
                // NOTE: I used getResources because it was the only way I could get it to work for Kotlin but says it has been deprecated for Java so hopefully this is okay
                if (articleFaved == 0) {
                    articleFaved = 1
                    floatingFavButton.setColorFilter(getResources().getColor(R.color.colorButtonActive))
                } else {
                    articleFaved = 0
                    floatingFavButton.setColorFilter(getResources().getColor(R.color.colorButtonInactive))
                }

                // Start brief hop animation
                favSpring.setFinalPosition(floatingFavButton.y)
                favAnim.setStartVelocity(1000f)
                favAnim.start()
            }
        }
    }

    // VVV Methods placed by default layout to fill action bar with basic functionality (more/settings) VVV

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
