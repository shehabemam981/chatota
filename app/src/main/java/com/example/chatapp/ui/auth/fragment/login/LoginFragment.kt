package com.example.chatapp.ui.auth.fragment.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.FragmentLoginBinding
import com.example.chatapp.myDateBase.constants
import com.example.chatapp.ui.auth.MainActivity
import com.example.chatapp.ui.auth.fragment.login.viewModel.LoginViewModel
import com.example.chatapp.ui.home.HomeActivity

class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {


    override fun initViewModel(): LoginViewModel {
        return ViewModelProvider(this)[LoginViewModel::class.java]

    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_login
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        navigateToSignUp()
        navigateToHome()
        viewModel.checkUserInHome()
    }

    private fun navigateToHome() {
        viewModel.event.observe(viewLifecycleOwner) { event ->
            when (event) {
                is LoginViewEvent.navidgateToHome -> {
                    startActivity(
                        Intent(
                            requireActivity(),
                            HomeActivity::class.java
                        ).putExtra(constants.passedUser, event.users)
                    )
                  requireActivity().finish()
                }
            }

        }
    }

    private fun initView() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }

    private fun navigateToSignUp() {
        binding.regiesterNowButton.setOnClickListener {
            (activity as MainActivity).navController.navigate(R.id.signUpFragment)
        }
    }


}