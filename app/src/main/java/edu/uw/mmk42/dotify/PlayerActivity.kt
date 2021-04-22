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
    private lateinit var btnChangeUser: Button
    private lateinit var imgPlayPrev: ImageView
    private lateinit var imgPlayNext: ImageView
    private lateinit var imgPlay: ImageView
    private lateinit var txtNumPlays: TextView
    private lateinit var txtUsername: TextView
    private lateinit var edtxtUsername: EditText
    private lateinit var imgSongCover: ImageView
    private lateinit var txtSongTitle: TextView
    private lateinit var txtArtistName: TextView
    private var randomNumber = Random.nextInt(1000, 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("created_1_mmk42", "created!")
        super.onCreate(savedInstanceState)
        Log.i("created_2_mmk42", "created!")
        setContentView(R.layout.activity_player_activity)
        Log.i("created_3_mmk42", "created!")
        binding = ActivityPlayerActivityBinding.inflate(layoutInflater).apply{setContentView(root)}
        Log.i("created_4_mmk42", "created!")

        with(binding) {
            val song: Song? = intent.getParcelableExtra<Song>(SONG_KEY)
            txtArtistName.text = song?.artist
            txtSongTitle.text = song?.title
            //imgSongCover.setImageResource(song.largeImageID)

            // Click Listeners
            /*
            btnChangeUser.setOnClickListener {
                changeUserClicked(btnChangeUser)
            }*/
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

        }
    }
    /*
    // changes the username
    fun changeUserClicked(btnChangeUser: Button) {
        //
        if(txtUsername.visibility == View.GONE) {
            if(edtxtUsername.text.toString() == ""){
                Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                txtUsername.visibility = View.VISIBLE
                edtxtUsername.visibility = View.GONE
                txtUsername.text = edtxtUsername.text
                btnChangeUser.text = "Change User"
            }
        } else {
            txtUsername.visibility = View.GONE
            edtxtUsername.visibility = View.VISIBLE
            btnChangeUser.text = "Apply"
        }

    }*/

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
        randomNumber = randomNumber + 1
        txtNumPlays.text = "$randomNumber plays"

    }
    // chenges the color or the number of plays when the song cover is long clicked
    fun songCoverLongClick(){
        txtNumPlays.setTextColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))

    }
}