package com.example.chatapp.ui.auth.signUp

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.myDateBase.MyDataBase
import com.example.chatapp.myDateBase.Users
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class SignUpViewModel:BaseViewModel() {
    var userNameLiveData = MutableLiveData<String>()
    var erroremailLiveData = MutableLiveData<String?>()
    var errorpasswordComfirmLiveData = MutableLiveData<String?>()
    var errorpasswordLiveData = MutableLiveData<String?>()
    var erroruserNameLiveData = MutableLiveData<String?>()
    var emailLiveData = MutableLiveData<String>()
    var passwordComfirmLiveData = MutableLiveData<String>()
    var passwordLiveData = MutableLiveData<String>()
    var auth = Firebase.auth
    val event= MutableLiveData<SignUpViewEvent>()
    fun signUp(){
    if(!isValidate())return
         auth.createUserWithEmailAndPassword(emailLiveData.value!!,passwordLiveData.value!!).addOnCompleteListener { task->
              if(task.isSuccessful){
                  setUserFromDataBaseAndNavigateToHome(task.result.user!!.uid)
              }else{
                  viewMessage.value = task.exception?.localizedMessage
              }
        }


    }

    private fun setUserFromDataBaseAndNavigateToHome(uid:String) {
        val user = Users(
           uid,
            userNameLiveData.value,
            emailLiveData.value,
        )
        MyDataBase.setDatainFireBase(user){task->
           if(task.isSuccessful){
               navigatetohome(user)
               viewMessage.value = "Created Account"
           } else{
           viewMessage.value = task.exception?.localizedMessage
           }
        }

    }

    private fun navigatetohome(users: Users) {
    event.value = SignUpViewEvent.navigateSignUptoHome(users)
    }
    fun navigateToSignUp(){
        event.value = SignUpViewEvent.navigateToSignUp()
    }

    private fun isValidate(): Boolean {
       var isValid = true
      if(userNameLiveData.value.isNullOrBlank()){
          erroruserNameLiveData.value ="Enter your user name "
          isValid = false
      }
        if(emailLiveData.value.isNullOrBlank()){
            erroremailLiveData.value ="Enter your email "
            isValid = false
        }
        if(passwordLiveData.value.isNullOrBlank()){
            errorpasswordLiveData.value ="Enter your password "
            isValid = false
        }
        if(passwordComfirmLiveData.value.isNullOrBlank()){
            errorpasswordComfirmLiveData.value ="Enter your password comfirm "
            isValid = false
        }
        if(passwordLiveData.value!! != passwordComfirmLiveData.value!!){
            errorpasswordComfirmLiveData.value = "its not match password"
            isValid = false
        }
        return isValid
    }

}