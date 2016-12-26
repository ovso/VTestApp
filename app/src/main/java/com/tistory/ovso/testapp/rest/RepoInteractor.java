package com.tistory.ovso.testapp.rest;

import com.tistory.ovso.testapp.model.Repo;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class RepoInteractor {

    private Call<List<Repo>> mCall;
    public void execute(String user) {

        OkHttpClient.Builder client = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        client.addInterceptor(loggingInterceptor);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")//https://api.github.com/users/:username
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create()) // create(gson)
                .build();

        Service service = retrofit.create(Service.class);
        mCall = service.create(user);
        mCall.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> repoList, Response<List<Repo>> response) {

                if(response.isSuccessful()) {
                    List<Repo> repos = response.body();
                    mOnResultListener.onRepo(repos);
                } else {
                    mOnResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                t.printStackTrace();
                mOnResultListener.onFail();
            }
        });
    }

    private OnResultListener mOnResultListener;
    public void setOnResultListener(OnResultListener listener) {
        mOnResultListener = listener;
    }


    public void cancel() {
        mCall.cancel();
    }

    public interface OnResultListener {
        void onRepo(List<Repo> repoList);
        void onFail();
    }

    public interface Service {
        //@FormUrlEncoded
        @GET("users/{username}/repos")//https://api.github.com/users/JakeWharton
        Call<List<Repo>> create(@Path("username") String username);
    }
}