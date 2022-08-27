package com.example.mdc

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import  com.example.mdc.databinding.ActivityScrollingBinding
import com.google.android.material.bottomappbar.BottomAppBar

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.bottomAppBar) {
            binding.fab.setOnClickListener {
                fabAlignmentMode =
                    if (fabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) BottomAppBar.FAB_ALIGNMENT_MODE_END else BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            }
            setNavigationOnClickListener {
                Snackbar.make(binding.root, R.string.message_action_success, Snackbar.LENGTH_LONG)
                    .setAnchorView(binding.fab)
                    .show()
            }
        }

        with(binding.content) {
            btnSkip.setOnClickListener {
                cvAd.visibility = View.GONE
            }
            btnBuy.setOnClickListener {
                Snackbar.make(it, R.string.card_btn_buying, Snackbar.LENGTH_LONG)
                    .setAnchorView(binding.fab)
                    .setAction(R.string.card_to_go) {
                        Toast.makeText(
                            this@ScrollingActivity,
                            R.string.card_historial,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .show()
            }

            Glide.with(this@ScrollingActivity)
                .load("https://i.ytimg.com/vi/uNhAHzUpsXQ/hqdefault.jpg")
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(this.imgCover)

            cbEnablePass.setOnClickListener {
                tilPassword.isEnabled = !tilPassword.isEnabled
            }

            etUrl.setOnFocusChangeListener { view, focused ->
                if (!focused) {

                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
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