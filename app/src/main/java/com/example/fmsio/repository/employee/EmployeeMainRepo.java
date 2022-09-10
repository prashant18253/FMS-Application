package com.example.fmsio.repository.employee;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.fmsio.firebaseLiveData.DocumentCallBack;
import com.example.fmsio.firebaseLiveData.FirebaseDocumentLiveData;
import com.example.fmsio.firebaseLiveData.FirebaseQueryLiveData;
import com.example.fmsio.firebaseLiveData.QueryCallBack;
import com.example.fmsio.model.Ticket;
import com.example.fmsio.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.util.ArrayList;
import java.util.Objects;

public class EmployeeMainRepo {
    public static void raiseTicket(String serviceType, String buildingName, String floorNo, String roomNo, String description, EmployeeMainRepo.RaiseTicketCallBack callBack) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        if(validate(serviceType, buildingName, floorNo, roomNo, callBack)){
            String uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
            DocumentReference docRef = db.collection("tickets").document();
            String id = docRef.getId();
            Ticket ticket = new Ticket(id,"employee",uid,uid,serviceType,System.currentTimeMillis()+"",Integer.parseInt(roomNo),buildingName,Integer.parseInt(floorNo),description);
            docRef.set(ticket).addOnCompleteListener(task -> {
                if(task.isSuccessful()){
                    callBack.onSuccess("Ticket Raised Successfully");
                }else{
                    callBack.onFailure("Error in raising ticket. Please try again later");
                }
            });
        }
    }



    //    public static void signOut(){
//        FirebaseAuth mAuth = FirebaseAuth.getInstance();
//        mAuth.signOut();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if(currentUser != null)
//            callBack.onSuccess("Signed Out Successfully");
//        else
//            callBack.onFailure("Couldn't sign out, Try Again!");
//    }
    private static boolean validate(String serviceType, String buildingName, String floorNo, String roomNo, EmployeeMainRepo.RaiseTicketCallBack callBack){
        if(serviceType.equals("Choose a service type")){
            callBack.onValidate(0, "Please choose a service");
            return false;
        }

        if(buildingName.equals("Choose a building")){
            callBack.onValidate(0, "Please choose a building");
            return false;
        }

        if(!isNum(floorNo)){
            callBack.onValidate(0, "Please enter a valid Floor No.");
            return false;
        }

        if(!isNum(roomNo)){
            callBack.onValidate(0, "Please enter a valid Room No.");
            return false;
        }

        callBack.onValidate(1, "Validation Successful");
        return true;
    }

    private static boolean isNum(String num){
        try {
            Integer.parseInt(num);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static LiveData<ArrayList<Ticket>> getNonCloseTickets(QueryCallBack callBack) {
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        String name = "";
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("employees").document(uid);
        //Log.d("Name", name);
        Query query = db.collection("tickets").whereNotEqualTo("status", "closed").whereEqualTo("assignedTo", "employee");
        return new FirebaseQueryLiveData<>(query, Ticket.class, callBack);
    }

    public static LiveData<ArrayList<Ticket>> getAllTickets(QueryCallBack callBack){
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        String name = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("tickets").whereEqualTo("assignedTo", "employee");
        //Log.d("Name", name);
        return new FirebaseQueryLiveData<>(query, Ticket.class, callBack);
    }

    public static LiveData<ArrayList<Ticket>> getOpenedTickets(QueryCallBack callBack){
        String name = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("tickets").whereEqualTo("status", "opened").whereEqualTo("assignedTo", "employee");
        return new FirebaseQueryLiveData<>(query, Ticket.class, callBack);
    }

    public static LiveData<ArrayList<Ticket>> getAssignedTickets(QueryCallBack callBack){
        String name = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("tickets").whereEqualTo("status", "assigned").whereEqualTo("assignedTo", "employee");
        return new FirebaseQueryLiveData<>(query, Ticket.class, callBack);
    }

    public static LiveData<ArrayList<Ticket>> getClosedTickets(QueryCallBack callBack){
        String name = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("tickets").whereEqualTo("status", "closed").whereEqualTo("assignedTo", "employee");
        return new FirebaseQueryLiveData<>(query, Ticket.class, callBack);
    }

    public static LiveData<ArrayList<Ticket>> getLowTickets(QueryCallBack callBack){
        String name = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("tickets").whereEqualTo("priority", "low").whereEqualTo("assignedTo", "employee");
        return new FirebaseQueryLiveData<>(query, Ticket.class, callBack);
    }

    public static LiveData<ArrayList<Ticket>> getMediumTickets(QueryCallBack callBack){
        String name = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("tickets").whereEqualTo("priority", "medium").whereEqualTo("assignedTo", "employee");
        return new FirebaseQueryLiveData<>(query, Ticket.class, callBack);
    }

    public static LiveData<ArrayList<Ticket>> getHighTickets(QueryCallBack callBack){
        String name = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getDisplayName();
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("tickets").whereEqualTo("priority", "high").whereEqualTo("assignedTo", "employee");
        return new FirebaseQueryLiveData<>(query, Ticket.class, callBack);
    }




    public static LiveData<User> getAdminProfile(DocumentCallBack callBack){
        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("employees").document(uid);
        return new FirebaseDocumentLiveData<>(docRef, User.class, callBack);
    }

    public static LiveData<ArrayList<User>> getAllEmployess(QueryCallBack callBack) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("employees");
        return new FirebaseQueryLiveData<>(query, User.class, callBack);
    }

    public static LiveData<ArrayList<Ticket>> noOfOpenTickets(QueryCallBack callBack) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("tickets").whereEqualTo("status", "opened");
        return new FirebaseQueryLiveData<>(query, Ticket.class, callBack);
    }

    public static LiveData<ArrayList<Ticket>> noOfAssignedTickets(QueryCallBack callBack) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("tickets").whereEqualTo("status", "assigned");
        return new FirebaseQueryLiveData<>(query, Ticket.class, callBack);
    }

    public static LiveData<ArrayList<Ticket>> noOfClosedTickets(QueryCallBack callBack) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Query query = db.collection("tickets").whereEqualTo("status", "closed");
        return new FirebaseQueryLiveData<>(query, Ticket.class, callBack);
    }

    public interface RaiseTicketCallBack{
        void onValidate(int flag, String msg);
        void onFailure(String msg);
        void onSuccess(String msg);
    }

    public interface AddEmployeeCallBack {
        void onFailure(String msg);
        void onSuccess(String msg);
    }

    public interface noOfTicketsStatusCallBack {
        void onFailureOfnoOfTicketsStatus(String msg);
        void onSuccessOfnoOfTicketsStatus(String msg);
    }
}
