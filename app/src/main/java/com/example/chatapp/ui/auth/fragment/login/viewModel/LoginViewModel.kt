package com.example.chatapp.ui.auth.fragment.login.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chatapp.base.BaseViewModel
import com.example.chatapp.myDateBase.MyDataBase
import com.example.chatapp.myDateBase.Users
import com.example.chatapp.ui.auth.fragment.login.LoginViewEvent
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class LoginViewModel :BaseViewModel() {

    val emailLiveData = MutableLiveData<String>()
    val passLiveData =MutableLiveData<String>()
    val errorEmailLiveData = MutableLiveData<String?>()
    val errorPassLiveData = MutableLiveData<String?>()
    val auth = Firebase.auth
    val event = MutableLiveData<LoginViewEvent>()
    fun Login(){
        if(!isValidate())return
     auth.signInWithEmailAndPassword(
         emailLiveData.value!!,
         passLiveData.value!!,
     ).addOnCompleteListener {task->
         if(task.isSuccessful) {
             getDataFromDataBase(task.result.user!!.uid)
         }else{
             viewMessage.value = task.exception!!.localizedMessage
         }
     }

    }

    private fun getDataFromDataBase(uid: String) {

      MyDataBase.getDataFromFireBase(uid){task->
          val users = task.result.toObject(Users::class.java)
          if(task.isSuccessful && users !=null){
          event.value = LoginViewEvent.navidgateToHome(users = users)
      }else{
          viewMessage.value = task.exception?.localizedMessage?:"something was wrong"
      }
      }
    }

    fun isValidate():Boolean{
     var isValid =true
    if(emailLiveData.value.isNullOrBlank()){
        errorEmailLiveData.value = "please,Enter your email"
    isValid = false
    } else {
        errorEmailLiveData.value = null
    }
        if (passLiveData.value.isNullOrBlank()) {
            errorPassLiveData.value = "please, Enter your password"
            isValid = false
        } else if (passLiveData.value!!.length < 6) {
            errorPassLiveData.value = "password must be at least 6 chars"
            isValid = false
        } else {
            errorPassLiveData.value = null
        }
    return isValid
}

   fun checkUserInHome(){
       val user  =  Firebase.auth.currentUser?:return
       event.value = LoginViewEvent.navidgateToHome(Users(user.uid,user.displayName,user.email))
   }
}