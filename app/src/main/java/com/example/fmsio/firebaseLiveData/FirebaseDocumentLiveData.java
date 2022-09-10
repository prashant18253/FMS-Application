package com.example.fmsio.firebaseLiveData;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class FirebaseDocumentLiveData<T> extends LiveData<T> {
    private ListenerRegistration registration;
    private DocumentReference documentReference;
    private Class aClass;
    private DocumentCallBack callBack;

    public FirebaseDocumentLiveData(DocumentReference documentReference,Class aClass,DocumentCallBack callBack){
        this.aClass = aClass;
        this.documentReference = documentReference;
        this.callBack = callBack;
    }

    private EventListener<DocumentSnapshot> eventListener = new EventListener<DocumentSnapshot>() {
        @Override
        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
            if(e!=null){
                callBack.onReadFailure();
                return;
            }
            if(documentSnapshot!=null && documentSnapshot.exists()){
                callBack.onReadSuccess();
                T item = (T)documentSnapshot.toObject(aClass);
                setValue((T)item);
            }
        }
    };

    @Override
    protected void onActive() {
        super.onActive();
        registration = documentReference.addSnapshotListener(eventListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        if(!hasActiveObservers()){
            registration.remove();
            registration = null;
        }
    }
}
