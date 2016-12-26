package com.tistory.ovso.testapp;

import com.tistory.ovso.testapp.model.Info;
import com.tistory.ovso.testapp.model.Repo;
import com.tistory.ovso.testapp.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MainModel {
    private User mUser;
    public boolean setUser(User user) {
        mUser = user;
        return true;
    }

    private List<Info> mInfoList = new ArrayList<>();
    public List<Info> getList() {
        mInfoList.add(mUser);
        mInfoList.addAll(mRepoList);
        return mInfoList;
    }


    private List<Repo> mRepoList;
    public void setRepoList(List<Repo> repoList) {
        mRepoList = repoList;
        Collections.sort(repoList, new Comparator<Repo>(){
            public int compare(Repo obj1, Repo obj2)
            {
                int o1 = Integer.parseInt(obj1.stargazers_count);
                int o2 = Integer.parseInt(obj2.stargazers_count);
                return (o1 > o2 ? -1: (o1 > o2) ? 1:0);
            }
        });
    }

}
