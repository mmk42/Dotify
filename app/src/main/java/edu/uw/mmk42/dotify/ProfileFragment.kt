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
            lifecycleScope.launch {
                val user = userRepository.getUser()
                val username = user.username
                val firstName = user.firstName
                val lastName = user.lastName
                val hasNose = user.hasNose
                val platform = user.platform

                txtName.text = "Name: " + firstName + " " + lastName
                txtUsername.text = "Username: " + username
                txtHasNose.text = "Has Nose: " + hasNose.toString()
                txtPlatform.text = "Platform: " + platform.toString()
                //Toast.makeText(requireContext(), user.username, Toast.LENGTH_SHORT).show()
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