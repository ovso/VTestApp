package com.tistory.ovso.testapp;


import android.content.Context;

public interface MainPresenter {

    void onCreate();

    interface View {

        Context getContext();

        void setRootView();

        void setToolbar();

        void setRecyclerViewAdapter();
    }
}
