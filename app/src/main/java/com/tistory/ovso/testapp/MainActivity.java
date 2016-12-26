package com.tistory.ovso.testapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainPresenter.View {

    private Unbinder mUnbinder;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private MainPresenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainPresenterImpl(this);
        mPresenter.onCreate();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_main);
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    public void setToolbar() {
        setSupportActionBar(mToolbar);
    }

    @Override
    public void setRecyclerViewAdapter() {

    }
}
