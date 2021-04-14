package edu.uw.mmk42.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var btnChangeUser: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("created_1_mmk42", "created!")
        btnChangeUser = findViewById(R.id.btnChangeUser)
        Log.i("created_3_mmk42", "created")
        btnChangeUser.setOnClickListener {
            changeUserClicked(btnChangeUser)
        }
        Log.i("created_2_mmk42", "created")
    }

    fun changeUserClicked(btnChangeUser: Button) {
        // add in button visibility later
        Log.i("testingmmk42", "clicking works")
        Toast.makeText(this, "change user", Toast.LENGTH_SHORT).show()
    }
}