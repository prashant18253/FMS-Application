package com.example.fmsio.repository.user;

import com.example.fmsio.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class UserRegisterRepo {
    public static void register(String name, String email, String phone, String password, String conf_password, RegistrationCallBack callBack){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if(validate(email, name, password, conf_password, phone, callBack)){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    callBack.onSuccess(0,"Verification Email has been sent");
                    String id = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                    User user = new User(id, name, email,"IIITD", phone,"user");

                    db.collection("users").document(id).set(user);
                    FirebaseUser _user = mAuth.getCurrentUser();
                    _user.sendEmailVerification();
                    mAuth.signOut();
                }else{
                    callBack.onFailure(Objects.requireNonNull(task.getException()).getMessage());
                }
            });

        }
    }

    private static boolean validate(String email, String name, String password, String confPassword, String mobileNo, RegistrationCallBack callBack) {
        if(name.equals("")){
            callBack.validate(0, "Enter a valid name");
            return false;
        }
        if(mobileNo.equals("") || !(mobileNo.length()==10) || !mobileNo.matches("[0-9]+")){
            callBack.validate(0, "Enter a valid Mobile No.");
            return false;
        }
        if(email.equals("")){
            callBack.validate(0, "Enter a valid Email Id");
            return false;
        }
        if(password.equals("")){
            callBack.validate(0, "Enter a password");
            return false;
        }
        if(confPassword.equals("")){
            callBack.validate(0, "Enter confirm password");
            return false;
        }

        if(!password.equals(confPassword)){
            callBack.validate(-1, "Passwords do not match");
            return false;
        }
        callBack.validate(1, "Validation Successful");
        return true;
    }

    public interface RegistrationCallBack{
        void validate(int flag,String text);
        void onSuccess(int flag,String text);
        void onFailure(String text);
    }
}
