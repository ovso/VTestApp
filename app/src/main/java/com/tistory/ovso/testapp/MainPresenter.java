package com.tistory.ovso.testapp;


import android.content.Context;

import com.tistory.ovso.testapp.model.Info;

import java.util.List;

public interface MainPresenter {

    void onCreate();

    interface View {

        Context getContext();

        void setRootView();

        void setToolbar();

        void setRecyclerViewAdapter(List<Info> repoList);

        void setUser(String name, String avatar_url);
    }
}
