package com.tistory.ovso.testapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tistory.ovso.testapp.model.Info;
import com.tistory.ovso.testapp.model.Repo;
import com.tistory.ovso.testapp.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int VIEW_TYPE_USER = 0;
    private final static int VIEW_TYPE_REPO = 1;

    private List<Info> mList;

    RecyclerViewAdapter(List<Info> list) {
        mList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_USER) {
            return new UserViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item_user, parent, false));
        } else {
            return new RepoViewHolder(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recyclerview_item, parent, false));

        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(position < 1) {
            setUser(holder, position);
        } else {
            setRepo(holder, position);
        }

    }

    private void setRepo(RecyclerView.ViewHolder holder, int position) {
        Repo repo = (Repo) mList.get(position);
        RepoViewHolder viewHolder = (RepoViewHolder) holder;
        viewHolder.mNameTv.setText(repo.name);
        viewHolder.mDescTv.setText(repo.description);
        viewHolder.mStartCountTv.setText(repo.stargazers_count);

    }

    private void setUser(RecyclerView.ViewHolder holder, int position) {
        User user = (User) mList.get(position);
        UserViewHolder viewHolder = (UserViewHolder) holder;
        viewHolder.mNameTv.setText(user.name);
        Glide.with(viewHolder.mNameTv.getContext()).load(user.avatar_url).into(
                viewHolder.userIv
        );

    }

    @Override
    public int getItemViewType(int position) {
        if(position < 1) {
            return VIEW_TYPE_USER;
        } else {
            return VIEW_TYPE_REPO;
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    final static class RepoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_textview)
        TextView mNameTv;

        @BindView(R.id.startcount_textview)
        TextView mStartCountTv;
        @BindView(R.id.desc_textview)

        TextView mDescTv;

        public RepoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
    final static class UserViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name_textview)
        TextView mNameTv;

        @BindView(R.id.user_imageview)
        ImageView userIv;

        public UserViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }
}