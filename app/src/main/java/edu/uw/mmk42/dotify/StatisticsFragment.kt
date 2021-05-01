package edu.uw.mmk42.dotify

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.uw.mmk42.dotify.databinding.FragmentStatisticsBinding


class StatisticsFragment : Fragment() {

    val safeArgs: StatisticsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentStatisticsBinding.inflate(inflater)
        with(binding) {
            imgSongCover.setImageResource(safeArgs.song.largeImageID)
            txtClickNum.text = safeArgs.song.title + " has been played "+ safeArgs.playNum.toString() +" times"
        }

        return binding.root
    }


}