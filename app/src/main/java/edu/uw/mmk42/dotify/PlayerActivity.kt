package edu.uw.mmk42.dotify

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import com.ericchee.songdataprovider.Song
import edu.uw.mmk42.dotify.databinding.ActivityPlayerActivityBinding
import kotlin.random.Random
private const val SONG_KEY = "song"
private const val SONG_NAME_KEY = "song_name"
private const val SONG_ARTIST_KEY = "song_artist"


fun navigateToPlayerActivity(context: Context, song: Song) = with(context) {
    val intent = Intent(context, PlayerActivity::class.java).apply {
        val bundle = Bundle().apply {
            putParcelable(SONG_KEY, song)
        }
        putExtras(bundle)
    }
    startActivity(intent)
}
class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerActivityBinding
    private var randomNumber = Random.nextInt(1000, 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding = ActivityPlayerActivityBinding.inflate(layoutInflater).apply{setContentView(root)}

        with(binding) {
            val song: Song? = intent.getParcelableExtra<Song>(SONG_KEY)
            txtArtistName.text = song?.artist
            txtSongTitle.text = song?.title
            if (song != null) {
                imgSongCover.setImageResource(song.largeImageID)
            }


            // Click Listeners
            imgPlayPrev.setOnClickListener {
                playPrevClicked()
            }
            imgPlayNext.setOnClickListener {
                playNextClicked()
            }
            imgPlay.setOnClickListener {
                playClicked()
            }
            imgSongCover.setOnLongClickListener {
                songCoverLongClick()
                true
            }
            txtNumPlays.text = "$randomNumber plays"

            btnSettings.setOnClickListener{
                if(song != null) {
                    launchSettingsActivity(this@PlayerActivity, song, randomNumber)
                }
            }

        }
    }


    // Toasts when prev button is clicked
    fun playPrevClicked(){

        Toast.makeText(this, "Skipping to previous track", Toast.LENGTH_SHORT).show()
    }
    //Toasts when next button is clicked
    fun playNextClicked(){

        Toast.makeText(this, "Skipping to next track", Toast.LENGTH_SHORT).show()
    }
    // increments the number of plays when the play button is clicked
    fun playClicked(){
        with(binding){
            randomNumber = randomNumber + 1
            txtNumPlays.text = "$randomNumber plays"
        }


    }
    // chenges the color or the number of plays when the song cover is long clicked
    fun songCoverLongClick(){
        with(binding){
            txtNumPlays.setTextColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
        }
    }

    // up button
    override fun onSupportNavigateUp(): Boolean {
        //Handle when the up button is clicked
        finish()
        return super.onSupportNavigateUp()
    }
}