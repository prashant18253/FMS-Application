package com.example.fmsio.viewModel.employee;

import androidx.lifecycle.ViewModel;
import com.example.fmsio.repository.employee.EmployeeLoginRepo;
import com.google.firebase.auth.FirebaseUser;

public class EmployeeLoginViewModel extends ViewModel {
    public FirebaseUser getCurrentUser(){
        return EmployeeLoginRepo.getCurrentUser();
    }

    public void login(String email, String password, EmployeeLoginRepo.LoginCallBack callBack){
        EmployeeLoginRepo.login(email, password, callBack);
    }
}
