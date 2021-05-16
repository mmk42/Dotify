package edu.uw.mmk42.dotify

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import edu.uw.mmk42.dotify.databinding.FragmentProfileBinding
import edu.uw.mmk42.dotify.databinding.FragmentStatisticsBinding


class ProfileFragment : Fragment() {

    val safeArgs: ProfileFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentProfileBinding.inflate(inflater)
        with(binding) {
            btnRefresh.setOnClickListener {
                Log.i("btnRefresh", "Refresh button was clicked")
//                val user = safeArgs.user
//                val username = user.username
//                Log.i("btnRefresh-username", username)


                txtName.text = "Toto"
                txtUsername.text = "totobear"
            }
            //imgSongCover.setImageResource(safeArgs.song.largeImageID)
            //txtClickNum.text = safeArgs.song.title + " has been played "+ safeArgs.playNum.toString() +" times"
        }

        return binding.root
    }


}