package com.example.fmsio.activity.user;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fmsio.R;
import com.example.fmsio.activity.admin.AdminLoginActivity;
import com.example.fmsio.activity.admin.AdminMainActivity;
import com.example.fmsio.callBack.user.activity.UserLoginCallBack;
import com.example.fmsio.databinding.ActivityAdminLoginBinding;
import com.example.fmsio.databinding.ActivityUserLoginBinding;
import com.example.fmsio.dialogFragment.common.PasswordDialogFragment;
import com.example.fmsio.repository.user.UserLoginRepo;
import com.example.fmsio.viewModel.admin.AdminLoginViewModel;
import com.example.fmsio.viewModel.user.UserLoginViewModel;

import java.util.Objects;

public class UserLoginActivity extends AppCompatActivity implements UserLoginCallBack, UserLoginRepo.LoginCallBack {
    private UserLoginViewModel viewModel;
    private ActivityUserLoginBinding binding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();

        viewModel = new ViewModelProvider(this).get(UserLoginViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_user_login);
        binding.setContract(this);

        progressDialog = new ProgressDialog(this);

        if(viewModel.getCurrentUser() != null){
            Intent intent = new Intent(UserLoginActivity.this, UserMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onLoginClick() {
        viewModel.login(binding.etUserEmail.getText().toString().trim(), binding.etUserPassword.getText().toString().trim(), this);
    }

    @Override
    public void onRegisterClick() {
        Intent intent = new Intent(UserLoginActivity.this, UserRegistrationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onForgotPasswordClick() {
        PasswordDialogFragment dialogFragment = new PasswordDialogFragment();
        dialogFragment.show(getSupportFragmentManager(),"Forgot Password");
    }

    @Override
    public void onValidate(String text, int flag) {
        if(flag == 1){
            progressDialog.show();
            progressDialog.setContentView(R.layout.progress_layout);
            progressDialog.setCancelable(false);
            Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);
        }else{
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginSuccess() {
        progressDialog.dismiss();
        Intent intent = new Intent(UserLoginActivity.this,UserMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailure(String text) {
        progressDialog.dismiss();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        binding.etUserEmail.setText("");
        binding.etUserPassword.setText("");
    }
}