package edu.uw.mmk42.dotify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.ericchee.songdataprovider.Song
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.mmk42.dotify.databinding.ActivitySongListBinding

private const val CURR_SONG_KEY = "CURR_SONG_KEY"

class SongListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongListBinding
    private lateinit var currSong: Song
    private lateinit var songs: List<Song>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)


        title = "All Songs"

        binding = ActivitySongListBinding.inflate(layoutInflater).apply { setContentView(root) }
        if (savedInstanceState != null) {
            currSong = savedInstanceState?.getParcelable(CURR_SONG_KEY)!!
            with(binding) {
                tvCurrSong.text = root.context.getString(R.string.curr_song_format, currSong.title,currSong.artist)
                tvCurrSong.visibility = View.VISIBLE
            }
        }

        songs = SongDataProvider.getAllSongs()
        with(binding) {
            val adapter = SongListAdapter(songs)
            rvSongs.adapter = adapter

            adapter.onSongClickListener = {position, song ->
                currSong = song
                tvCurrSong.visibility = View.VISIBLE
                tvCurrSong.text = root.context.getString(R.string.curr_song_format, song.title,song.artist)
                //Toast.makeText(this@SongListActivity, "You clicked at pos: $position for $song", Toast.LENGTH_SHORT).show()

            }

            btnShuffle.setOnClickListener {
                // On Refresh Click, update the list
                adapter.updateSongs(songs.toMutableList().shuffled())
            }

            tvCurrSong.setOnClickListener {
                navigateToPlayerActivity(this@SongListActivity, currSong)
            }

            adapter.onSongLongClickListener = {position, song ->
                //currSong = song
                /*var newList = songs.toMutableList()
                newList.removeAt(position)
                songs = newList
                adapter.updateSongs(newList)*/
                val newSongs = songs.toMutableList().apply {
                    //Log.i("mmk42-11", "position to delete is $position")
                    remove(song)
                }
                songs = newSongs
                //Log.i("mmk42-12", "position to delete is $position")
                adapter.updateSongs(newSongs)
                Toast.makeText(this@SongListActivity, root.context.getString(R.string.remove_song_format, song.title), Toast.LENGTH_SHORT).show()
                true
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(CURR_SONG_KEY, currSong)
        super.onSaveInstanceState(outState)
    }

}