package edu.uw.mmk42.dotify

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var btnChangeUser: Button
    private lateinit var btnPlayPrev: ImageView
    private lateinit var btnPlayNext: ImageView
    private lateinit var btnPlay: ImageView
    private lateinit var txtNumPlays: TextView
    private lateinit var txtUsername: TextView
    private lateinit var edtxtUsername: EditText
    private lateinit var imgSongCover: ImageView
    private var randomNumber = Random.nextInt(1000, 10000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnChangeUser = findViewById(R.id.btnChangeUser)
        btnPlayPrev = findViewById(R.id.imgPlayPrev)
        btnPlayNext = findViewById(R.id.imgPlayNext)
        btnPlay = findViewById(R.id.imgPlay)
        txtNumPlays = findViewById(R.id.txtNumPlays)
        txtUsername = findViewById(R.id.txtUsername)
        edtxtUsername = findViewById(R.id.edtxtUsername)
        imgSongCover = findViewById(R.id.imgSongCover)

        // Click Listeners
        btnChangeUser.setOnClickListener {
            changeUserClicked(btnChangeUser)
        }
        btnPlayPrev.setOnClickListener{
            playPrevClicked()
        }
        btnPlayNext.setOnClickListener{
            playNextClicked()
        }
        btnPlay.setOnClickListener{
            playClicked()
        }
        imgSongCover.setOnLongClickListener{
            songCoverLongClick()
            true
        }
        txtNumPlays.text = "$randomNumber plays"
    }

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
        randomNumber = randomNumber + 1
        txtNumPlays.text = "$randomNumber plays"
    }
    // chenges the color or the number of plays when the song cover is long clicked
    fun songCoverLongClick(){
        txtNumPlays.setTextColor(Color.argb(255, Random.nextInt(256), Random.nextInt(256), Random.nextInt(256)))
    }
}