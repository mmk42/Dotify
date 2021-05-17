package edu.uw.mmk42.dotify

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.ericchee.songdataprovider.Song
import edu.uw.mmk42.dotify.databinding.ActivitySettingsBinding
import edu.uw.mmk42.dotify.model.User
import edu.uw.mmk42.dotify.repository.UserRepository
import kotlinx.coroutines.launch

private const val SONG_KEY = "song"
private const val PLAY_KEY = "playNum"
//private const val USER_KEY = "user"

fun launchSettingsActivity(context: Context, song: Song, playNum: Int) = with(context){
    startActivity(Intent(this, SettingsActivity::class.java).apply {
        putExtra(SONG_KEY, song)
        putExtra(PLAY_KEY, playNum)
    })
}

class SettingsActivity : AppCompatActivity() {

    private val navController by lazy { findNavController(R.id.navHost) }


    private lateinit var binding: ActivitySettingsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySettingsBinding.inflate(layoutInflater).apply { setContentView(root) }
        with(binding) {

            navController.setGraph(R.navigation.nav_graph, intent.extras)
            title = "Settings"
        }
        setupActionBarWithNavController(navController)
    }

    override fun onSupportNavigateUp() = navController.navigateUp()
}