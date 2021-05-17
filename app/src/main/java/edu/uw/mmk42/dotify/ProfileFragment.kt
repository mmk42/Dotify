package edu.uw.mmk42.dotify

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import edu.uw.mmk42.dotify.databinding.FragmentProfileBinding
import edu.uw.mmk42.dotify.databinding.FragmentStatisticsBinding
import edu.uw.mmk42.dotify.repository.UserRepository
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {

    val safeArgs: ProfileFragmentArgs by navArgs()
    //lateinit var dotifyApp: DotifyApplication
    private val dotifyApp: DotifyApplication by lazy { requireActivity().application as DotifyApplication }
    private val userRepository by lazy { dotifyApp.userRepository }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentProfileBinding.inflate(inflater)
        with(binding) {
            Log.i(" before lifecycle", "test")

            swipeToRefreshLayout.setOnRefreshListener {
                imgProfilePicture.load("https://raw.githubusercontent.com/echeeUW/codesnippets/master/voldemort.png")
            }

            lifecycleScope.launch {
                val user = userRepository.getUser()
                val username = user.username
                val firstName = user.firstName
                val lastName = user.lastName
                val hasNose = user.hasNose
                val platform = user.platform
                val profilePicURL = user.profilePicURL

                txtName.text = "Name: " + firstName + " " + lastName
                txtUsername.text = "Username: " + username
                txtHasNose.text = "Has Nose: " + hasNose.toString()
                txtPlatform.text = "Platform: " + platform.toString()
                imgProfilePicture.load(profilePicURL)

            }

            btnRefresh.setOnClickListener {

                Log.i("btnRefresh", "Refresh button was clicked")
                txtName.text = "Toto"
                txtUsername.text = "totobear"
            }
            //imgSongCover.setImageResource(safeArgs.song.largeImageID)
            //txtClickNum.text = safeArgs.song.title + " has been played "+ safeArgs.playNum.toString() +" times"
        }

        return binding.root
    }


}