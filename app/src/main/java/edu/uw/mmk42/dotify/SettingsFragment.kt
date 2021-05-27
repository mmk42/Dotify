package edu.uw.mmk42.dotify

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ericchee.songdataprovider.SongDataProvider
import edu.uw.mmk42.dotify.databinding.FragmentSettingsBinding
import edu.uw.mmk42.dotify.manager.SongSyncManager


class SettingsFragment : Fragment() {
    val safeArgs: SettingsFragmentArgs by navArgs()

    private val navController by lazy {findNavController()}

    private val DotifyApp by lazy {requireActivity().application as DotifyApplication}
    private val songSyncManager: SongSyncManager by lazy { DotifyApp.songSyncManager}
    private val songNotificationManager: SongNotificationManager by lazy { DotifyApp.songNotificationManager}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSettingsBinding.inflate(inflater)

        val song = safeArgs.song
        val playNum = safeArgs.playNum

        //val testtoast = recievedPlayNum.toString()
        //val testtoast = recievedSong.toString()
        //Toast.makeText(context, testtoast, Toast.LENGTH_SHORT).show()

        with(binding) {
            btnAbout.setOnClickListener{
                navController.navigate(R.id.aboutFragment)
                // Toast.makeText(requireContext(), "You clicked on about button", Toast.LENGTH_SHORT).show()
            }
            btnProfile.setOnClickListener{
                navController.navigate(R.id.profileFragment)
                //Toast.makeText(requireContext(), "You clicked on profile button", Toast.LENGTH_SHORT).show()
            }
            btnStatistics.setOnClickListener{
                //navController.navigate(R.id.statisticsFragment)
                navController.navigate(SettingsFragmentDirections.actionSettingsFragmentToStatisticsFragment(song, playNum))
                //Toast.makeText(requireContext(), "You clicked on stats button", Toast.LENGTH_SHORT).show()
            }

//            btnTest.setOnClickListener {
//                // execute work in the background
//                songSyncManager.syncSongs()
//            }
//
//            btnTestPeriodically.setOnClickListener {
//                // execute work in the background
//                songSyncManager.syncSongsPeriodically()
//            }
//
//            btnTestNotify.setOnClickListener {
//                songNotificationManager.publishNewSongNotification(SongDataProvider.getAllSongs().random())
//                //songSyncManager.syncSongsPeriodically()
//            }

            songNotificationManager.isNotificationsEnabled = switchNotificationsEnabled.isChecked

            switchNotificationsEnabled.setOnCheckedChangeListener {_, isChecked ->

                songNotificationManager.isNotificationsEnabled = isChecked
                //songNotificationManager.publishNewSongNotification(SongDataProvider.getAllSongs().random())
                if(isChecked) {
                    songSyncManager.syncSongsPeriodically()
                    Log.i("SettingsFragment", "notifications is checked")
                } else {
                    songSyncManager.stopPeriodicallySyncing()
                    Log.i("SettingsFragment", "notifications is un-checked")
                }
            }
        }

        return binding.root
    }
}