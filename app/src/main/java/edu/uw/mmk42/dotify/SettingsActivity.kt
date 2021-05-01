package edu.uw.mmk42.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import edu.uw.mmk42.dotify.databinding.ActivitySettingsBinding

fun launchSettingsActivity(context: Context) = with(context){
    startActivity(Intent(this, SettingsActivity::class.java).apply {
        // putExtra()
    })
}

class SettingsActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater).apply { setContentView(root) }
        with(binding) {
            title = "Settings"
        }
    }
}