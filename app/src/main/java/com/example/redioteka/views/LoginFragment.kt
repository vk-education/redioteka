package com.example.redioteka.views

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.redioteka.databinding.LoginFragmentBinding
import com.example.redioteka.viewmodels.State
import com.example.redioteka.viewmodels.UserViewModel

class LoginFragment: Fragment() {
    protected val viewModel by viewModels<UserViewModel>()
    protected var _binding: LoginFragmentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LoginFragmentBinding.inflate(inflater)
        return binding().root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding().loginButton.setOnClickListener { login() }

        viewModel.state().observe(viewLifecycleOwner) {
            when(it) {
                is State.Success -> {
                    //val intent = Intent(getView()?.context, MovieView::class.java)
                    //startActivity(intent)
                    Toast.makeText(getView()?.context, "Authorized", Toast.LENGTH_LONG).show()
                }
                is State.Fail -> {
                    Toast.makeText(getView()?.context, "Not Authorized", Toast.LENGTH_LONG).show()
                    val intent = Intent(getView()?.context, MovieView::class.java)
                    startActivity(intent)
                }
            }
        }
    }

    protected fun login() {
        val email: String = binding().editEmail.toString()
        val password: String = binding().editPassword.toString()
        viewModel.login(email, password)
    }

    protected fun binding() = _binding!!
}