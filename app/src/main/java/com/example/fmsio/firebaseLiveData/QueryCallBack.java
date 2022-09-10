package com.example.fmsio.firebaseLiveData;

public interface QueryCallBack {
    void onQueryReadError();
    void onQueryEmpty();
    void onQueryExistent();
}
