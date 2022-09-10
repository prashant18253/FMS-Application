package com.example.fmsio.viewModel.common;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.example.fmsio.repository.common.ChooseRoleRepo;

public class ChooseRoleViewModel extends ViewModel {
    public int getRole(Context context){
        return ChooseRoleRepo.getRole(context);
    }

    public void setRole(int role, Context context){
        ChooseRoleRepo.setRole(context, role);
    }
}
