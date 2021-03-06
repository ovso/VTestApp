package com.tistory.ovso.testapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.tistory.ovso.testapp.model.Info;

import java.util.List;

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
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    public void setRecyclerViewAdapter(List<Info> repoList) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(new RecyclerViewAdapter(repoList));
    }

}
