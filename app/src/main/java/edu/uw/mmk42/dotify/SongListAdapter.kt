package edu.uw.mmk42.dotify

import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ericchee.songdataprovider.Song
import edu.uw.mmk42.dotify.databinding.ItemSongBinding

class SongListAdapter(private var listOfSongs: List<Song>): RecyclerView.Adapter<SongListAdapter.SongViewHolder>() {

    var onSongClickListener: (position: Int, song: Song) -> Unit = {position, song ->}
    var onSongLongClickListener: (position: Int, song: Song) -> Unit = {position, song ->}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val binding = ItemSongBinding.inflate(LayoutInflater.from(parent.context))
        return SongViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = listOfSongs[position]
        with(holder.binding) {
            tvName.text = song.title
            tvArtist.text = song.artist
            ivCoverArt.setImageResource(song.smallImageID)
            itemRoot.setOnClickListener{
                onSongClickListener(position, song)
            }
            itemRoot.setOnLongClickListener{
                onSongLongClickListener(position, song)
                true
            }
        }
    }

    override fun getItemCount(): Int = listOfSongs.size


    fun updateSongs(newSongs: List<Song>) {
        //non-animated method
        /*
        this.listOfSongs = newSongs
        notifyDataSetChanged()*/
        // animated way of applying updates
        val callback = SongDiffCallback(listOfSongs, newSongs)
        val diffResult = DiffUtil.calculateDiff(callback)
        diffResult.dispatchUpdatesTo(this)
        // We update the list
        listOfSongs = newSongs
    }

    class SongViewHolder(val binding: ItemSongBinding): RecyclerView.ViewHolder(binding.root)

}