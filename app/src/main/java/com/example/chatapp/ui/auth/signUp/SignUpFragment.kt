package com.example.chatapp.ui.auth.signUp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chatapp.R
import com.example.chatapp.base.BaseFragment
import com.example.chatapp.databinding.FragmentSignUpBinding
import com.example.chatapp.myDateBase.Users
import com.example.chatapp.myDateBase.constants
import com.example.chatapp.ui.home.HomeActivity


class SignUpFragment : BaseFragment<FragmentSignUpBinding,SignUpViewModel>() {
    override fun initViewModel(): SignUpViewModel {
        return ViewModelProvider(this)[SignUpViewModel::class.java]
    }

    override fun getLayoutId(): Int {
        return R.layout.fragment_sign_up
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
       navigates()
    }

    private fun navigates() {
        viewModel.event.observe(viewLifecycleOwner){event->
            when(event){
                is SignUpViewEvent.navigateSignUptoHome->{
                   startActivity(Intent(requireActivity(),HomeActivity::class.java).putExtra(constants.passedUser,event.users))
                   requireActivity().finish()
                }
                is SignUpViewEvent.navigateToSignUp->{
                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                }
            }


        }
    }

    private fun initView() {
        binding.vm = viewModel
        binding.lifecycleOwner = this
    }




}