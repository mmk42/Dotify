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
    private lateinit var binding: FragmentProfileBinding

    private val dotifyApp: DotifyApplication by lazy { requireActivity().application as DotifyApplication }
    private val userRepository by lazy { dotifyApp.userRepository }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentProfileBinding.inflate(inflater)
        with(binding) {
            Log.i(" before lifecycle", "test")

            // on swipe down, refresh (reload all data from user and display in fragment)
            swipeToRefreshLayout.setOnRefreshListener {
                lifecycleScope.launch {
                    kotlin.runCatching {
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

                        txtUsername.visibility = View.VISIBLE
                        txtHasNose.visibility = View.VISIBLE
                        txtPlatform.visibility = View.VISIBLE
                        imgProfilePicture.visibility = View.VISIBLE
                    }.onFailure {
                        //Toast.makeText(requireContext(),"Error Occured", Toast.LENGTH_SHORT).show()
                        txtName.text = "An Error Has Occurred"
                        txtUsername.visibility = View.GONE
                        txtHasNose.visibility = View.GONE
                        txtPlatform.visibility = View.GONE
                        imgProfilePicture.visibility = View.GONE

                    }
                }
                swipeToRefreshLayout.isRefreshing=false
            }

            // ask about how to reduce redundancy
            lifecycleScope.launch {
                kotlin.runCatching {
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

                    txtUsername.visibility = View.VISIBLE
                    txtHasNose.visibility = View.VISIBLE
                    txtPlatform.visibility = View.VISIBLE
                    imgProfilePicture.visibility = View.VISIBLE
                }.onFailure {
                    txtName.text = "An Error Has Occurred"
                    txtUsername.visibility = View.GONE
                    txtHasNose.visibility = View.GONE
                    txtPlatform.visibility = View.GONE
                    imgProfilePicture.visibility = View.GONE
                }
            }

        }

        return binding.root
    }

//    private fun loadUser() {
//        Log.i("loadUser", "load user was clicked")
//
//       // with(binding) {
//            lifecycleScope.launch {
//                Log.i("loadUser", "before user loaded")
//                val user = userRepository.getUser()
//                Log.i("loadUser", "after user loaded")
//                val username = user.username
//                Log.i("loadUser", username)
//                val firstName = user.firstName
//                val lastName = user.lastName
//                val hasNose = user.hasNose
//                val platform = user.platform
//                val profilePicURL = user.profilePicURL
//
//                binding.txtName.text = "Name: " + firstName + " " + lastName
//                binding.txtUsername.text = "Username: " + username
//                binding.txtHasNose.text = "Has Nose: " + hasNose.toString()
//                binding.txtPlatform.text = "Platform: " + platform.toString()
//                binding.imgProfilePicture.load(profilePicURL)
//
//            }
//       // }
//
//    }

}