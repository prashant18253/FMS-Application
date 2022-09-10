package com.example.fmsio.viewModel.admin;

import androidx.lifecycle.ViewModel;

import com.example.fmsio.repository.admin.AdminLoginRepo;
import com.google.firebase.auth.FirebaseUser;

public class AdminLoginViewModel extends ViewModel {
    public FirebaseUser getCurrentUser(){
        return AdminLoginRepo.getCurrentUser();
    }

    public void login(String email, String password, AdminLoginRepo.LoginCallBack callBack){
        AdminLoginRepo.login(email, password, callBack);
    }
}
