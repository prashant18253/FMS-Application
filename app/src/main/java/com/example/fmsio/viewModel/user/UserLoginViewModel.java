package com.example.fmsio.viewModel.user;

import androidx.lifecycle.ViewModel;

import com.example.fmsio.repository.user.UserLoginRepo;
import com.google.firebase.auth.FirebaseUser;

public class UserLoginViewModel extends ViewModel {
    public FirebaseUser getCurrentUser(){
        return UserLoginRepo.getCurrentUser();
    }

    public void login(String email, String password, UserLoginRepo.LoginCallBack callBack){
        UserLoginRepo.login(email, password, callBack);
    }
}
