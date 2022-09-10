package com.example.fmsio.firebaseLiveData;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FirebaseQueryLiveData<T> extends LiveData<T> {
    private ListenerRegistration registration;
    private Query query;
    private Class aClass;
    private QueryCallBack callBack;

    public FirebaseQueryLiveData(Query query, Class aClass, QueryCallBack callBack) {
        this.query = query;
        this.aClass = aClass;
        this.callBack = callBack;
    }

    private EventListener<QuerySnapshot> listener = new EventListener<QuerySnapshot>() {
        @Override
        public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
            if(e!=null){
                callBack.onQueryReadError();
                return;
            }
            if(queryDocumentSnapshots!=null && queryDocumentSnapshots.isEmpty()){
                callBack.onQueryEmpty();
            }else if(queryDocumentSnapshots!=null && !queryDocumentSnapshots.isEmpty()){
                callBack.onQueryExistent();
                List<T> itemList = new ArrayList<>();
                for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {
                    T item = (T) snapshot.toObject(aClass);
                    itemList.add(item);
                }
                setValue((T) itemList);
            }

        }
    };

    @Override
    protected void onActive() {
        super.onActive();
        registration = query.addSnapshotListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if (!hasActiveObservers()) {
            registration.remove();
            registration = null;
        }
    }
}
