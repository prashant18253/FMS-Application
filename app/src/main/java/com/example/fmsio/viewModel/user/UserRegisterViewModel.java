package com.example.fmsio.viewModel.user;

import androidx.lifecycle.ViewModel;

import com.example.fmsio.repository.user.UserRegisterRepo;

public class UserRegisterViewModel extends ViewModel {
    public void register(String name, String email, String phone, String password, String conf_password, UserRegisterRepo.RegistrationCallBack callBack){
        UserRegisterRepo.register(name, email, phone, password, conf_password, callBack);
    }
}
