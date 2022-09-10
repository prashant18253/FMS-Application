package com.example.fmsio.repository.employee;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Objects;

public class EmployeeLoginRepo {
    public static FirebaseUser getCurrentUser(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        return mAuth.getCurrentUser();
    }

    public static void login(String email, String password, EmployeeLoginRepo.LoginCallBack callBack){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if(email.equals("")){
            callBack.onValidate(getErrorCode(1), 0);
        }else if(password.equals("")){
            callBack.onValidate(getErrorCode(2),0);
        }else {
            callBack.onValidate("Valid", 1);
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    String uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
                    final DocumentReference docRef = db.collection("employees").document(uid);
                    docRef.get().addOnCompleteListener(task1 -> {
                        DocumentSnapshot doc = task1.getResult();
                        if(doc != null && doc.exists()) {
                            callBack.onLoginSuccess();
                        } else {
                            mAuth.signOut();
                            callBack.onLoginFailure(getErrorCode(3));
                        }
                    });
                } else {
                    callBack.onLoginFailure(Objects.requireNonNull(task.getException()).getMessage());
                }
            });
        }
    }

    private static String getErrorCode(int i) {
        if(i == 1) return "Enter a valid Email Id";
        if(i == 2) return "Enter a password";
        if(i == 3) return "Not Your Territory";
        return "";
    }

    public interface LoginCallBack{
        void onValidate(String text, int flag);
        void onLoginSuccess();
        void onLoginFailure(String text);
    }
}
