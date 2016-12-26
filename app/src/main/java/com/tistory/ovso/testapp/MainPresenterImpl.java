package com.tistory.ovso.testapp;


import android.util.Log;

import com.tistory.ovso.testapp.model.Repo;
import com.tistory.ovso.testapp.model.User;
import com.tistory.ovso.testapp.rest.RepoInteractor;
import com.tistory.ovso.testapp.rest.UserInteractor;

import java.util.List;

public class MainPresenterImpl implements MainPresenter {

    private MainPresenter.View mView;
    private UserInteractor mUserInteractor;
    private RepoInteractor mRepoInteractor;
    private MainModel mModel;
    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
        mModel = new MainModel();
        mUserInteractor = new UserInteractor();
        mRepoInteractor = new RepoInteractor();
        mUserInteractor.setOnResultListener(mOnUserResultListener);
        mRepoInteractor.setOnResultListener(mOnRepoResultListener);
    }
    private RepoInteractor.OnResultListener mOnRepoResultListener = new RepoInteractor.OnResultListener() {
        @Override
        public void onRepo(List<Repo> repoList) {
            mModel.setRepoList(repoList);
            mView.setRecyclerViewAdapter(mModel.getList());
        }

        @Override
        public void onFail() {

        }
    };
    private UserInteractor.OnResultListener mOnUserResultListener = new UserInteractor.OnResultListener() {
        @Override
        public void onUser(User user) {
            if(mModel.setUser(user)) {
                mRepoInteractor.execute("JakeWharton");
            }
        }

        @Override
        public void onFail() {

        }
    };
    @Override
    public void onCreate() {
        mUserInteractor.execute("JakeWharton");
        mView.setRootView();
        mView.setToolbar();
    }
}
