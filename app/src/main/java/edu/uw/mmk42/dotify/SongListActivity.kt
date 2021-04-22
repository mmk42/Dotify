package edu.uw.mmk42.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.mmk42.dotify.databinding.ActivityMainBinding
import edu.uw.mmk42.dotify.databinding.ActivitySongListBinding

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }
        //val songs = listOf("dua lipa", "justin", "taylor swift", "lauv")
        val songs = SongDataProvider.getAllSongs()
        with(binding) {
            val adapter = SongListAdapter(songs)
            rvSongs.adapter = adapter

            adapter.onSongClickListener = {position, name ->
                Toast.makeText(this@SongListActivity, "You clicked at pos: $position for $name", Toast.LENGTH_SHORT).show()
            }

            btnShuffle.setOnClickListener {
                // On Refresh Click, update the list
                adapter.updateSongs(songs.toMutableList().shuffled())
            }
        }
    }
}