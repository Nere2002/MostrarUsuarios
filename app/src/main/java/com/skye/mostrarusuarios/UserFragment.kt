package com.skye.mostrarusuarios

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skye.mostrarusuarios.databinding.FragmentUserBinding

class UserFragment : Fragment() {
    private lateinit var binding: FragmentUserBinding

    companion object {
        const val ARG_USERNAME = "arg_username"
        const val ARG_PASSWORD = "arg_password"

        fun newInstance(username: String, password: String): UserFragment {
            val fragment = UserFragment()
            val args = Bundle()
            args.putString(ARG_USERNAME, username)
            args.putString(ARG_PASSWORD, password)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(ARG_USERNAME)
        val password = arguments?.getString(ARG_PASSWORD)

        binding.tvUsername.text = "Username: $username"
        binding.tvPassword.text = "Password: $password"
    }
}
