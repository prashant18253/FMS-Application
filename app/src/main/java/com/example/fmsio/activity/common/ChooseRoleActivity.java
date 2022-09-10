package com.example.fmsio.activity.common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import com.example.fmsio.R;
import com.example.fmsio.activity.admin.AdminLoginActivity;
import com.example.fmsio.activity.employee.EmployeeLoginActivity;
import com.example.fmsio.activity.user.UserLoginActivity;
import com.example.fmsio.callBack.common.activity.ChooseRoleCallBack;
import com.example.fmsio.databinding.ActivityChooseRoleBinding;
import com.example.fmsio.viewModel.common.ChooseRoleViewModel;

import java.util.Objects;

public class ChooseRoleActivity extends AppCompatActivity implements ChooseRoleCallBack {
    private ChooseRoleViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();

        viewModel = new ViewModelProvider(this).get(ChooseRoleViewModel.class);
        ActivityChooseRoleBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_choose_role);
        binding.setContract(this);

        int flag = viewModel.getRole(getApplicationContext());
        redirect(flag);
    }

    @Override
    public void onUserClick() {
        viewModel.setRole(0, getApplicationContext());
        redirect(0);
    }

    @Override
    public void onAdminClick() {
        viewModel.setRole(1, getApplicationContext());
        redirect(1);
    }

    @Override
    public void onEmployeeClick() {
        viewModel.setRole(2, getApplicationContext());
        redirect(2);
    }

    private void redirect(int flag){
        if(flag == 0){
            Intent intent = new Intent(this, UserLoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }else if(flag == 1){
            Intent intent = new Intent(this, AdminLoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }else if(flag == 2){
            Intent intent = new Intent(this, EmployeeLoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }
}