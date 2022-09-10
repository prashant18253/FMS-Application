package com.example.fmsio.activity.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.fmsio.R;
import com.example.fmsio.callBack.admin.activity.AdminLoginCallBack;
import com.example.fmsio.databinding.ActivityAdminLoginBinding;
import com.example.fmsio.dialogFragment.common.PasswordDialogFragment;
import com.example.fmsio.repository.admin.AdminLoginRepo;
import com.example.fmsio.viewModel.admin.AdminLoginViewModel;

import java.util.Objects;

public class AdminLoginActivity extends AppCompatActivity implements AdminLoginCallBack, AdminLoginRepo.LoginCallBack {

    private AdminLoginViewModel viewModel;
    private ActivityAdminLoginBinding binding;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();

        viewModel = new ViewModelProvider(this).get(AdminLoginViewModel.class);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_admin_login);
        binding.setContract(this);

        progressDialog = new ProgressDialog(this);

        if(viewModel.getCurrentUser() != null){
            Intent intent = new Intent(AdminLoginActivity.this, AdminMainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onLoginClick() {
        viewModel.login(binding.etAdminEmail.getText().toString().trim(), binding.etAdminPassword.getText().toString().trim(), this);
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
        Intent intent = new Intent(AdminLoginActivity.this,AdminMainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailure(String text) {
        progressDialog.dismiss();
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        binding.etAdminEmail.setText("");
        binding.etAdminPassword.setText("");
    }
}