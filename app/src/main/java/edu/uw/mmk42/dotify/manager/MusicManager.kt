package edu.uw.mmk42.dotify.manager

import android.util.Log
import android.widget.Toast

class MusicManager {
    private var numOfSongsClicked = 0

    fun onSongClicked() {
        numOfSongsClicked++
        Log.i("num songs", "num songs clicked is: $numOfSongsClicked")
    }
}