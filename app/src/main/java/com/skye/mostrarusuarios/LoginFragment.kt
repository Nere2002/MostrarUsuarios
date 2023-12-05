package com.skye.mostrarusuarios

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.skye.mostrarusuarios.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()

            // Simulando una lista de usuarios
            val users = listOf(
                User("usuario1", "contrasena1"),
                User("usuario2", "contrasena2"),
                User("usuario3", "contrasena3")
                // Agrega más usuarios
            )

            viewModel.loginUser(username, password, users)
        }

        viewModel.loginResult.observe(viewLifecycleOwner) { success ->
            if (success) {
                // Navegar al UserFragment con la información del usuario
                val username = binding.etUsername.text.toString()
                val password = binding.etPassword.text.toString()

                val userFragment = UserFragment.newInstance(username, password)
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, userFragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                //si el inicio de sesion no es correcto
                showErrorPopup()
            }
        }
    }
    private fun showErrorPopup() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Error de inicio de sesión")
        builder.setMessage("El nombre de usuario o la contraseña son incorrectos.")
        builder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}
