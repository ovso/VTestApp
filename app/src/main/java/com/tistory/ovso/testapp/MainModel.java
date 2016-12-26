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
        return mInfoList;
    }
    public void sort() {

    }
    private List<Repo> mRepoList;
    public void setRepoList(List<Repo> repoList) {
        mRepoList = repoList;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Collections.sort(mRepoList, new NameAscCompare());
                mInfoList.add(mUser);
                mInfoList.addAll(mRepoList);
            }
        }).start();
    }

    static class NameAscCompare implements Comparator<Repo> {

        /**
         * 오름차순(ASC)
         */
        @Override
        public int compare(Repo arg0, Repo arg1) {
            return arg0.stargazers_count.compareTo(arg1.stargazers_count);
        }

    }

}
