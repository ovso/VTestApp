package com.tistory.ovso.testapp;


import com.tistory.ovso.testapp.model.User;
import com.tistory.ovso.testapp.rest.UserInteractor;

public class MainPresenterImpl implements MainPresenter {

    private MainPresenter.View mView;
    private UserInteractor mUserInteractor;

    MainPresenterImpl(MainPresenter.View view) {
        mView = view;
        mUserInteractor = new UserInteractor();
        mUserInteractor.setOnResultListener(mOnUserResultListener);
    }
    private UserInteractor.OnResultListener mOnUserResultListener = new UserInteractor.OnResultListener() {
        @Override
        public void onUser(User user) {

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
        mView.setRecyclerViewAdapter();
    }
}
