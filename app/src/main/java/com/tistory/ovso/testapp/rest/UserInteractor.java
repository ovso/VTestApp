package com.tistory.ovso.testapp.rest;

import com.tistory.ovso.testapp.model.User;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class UserInteractor {

    private Call<User> mCall;
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
        mCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful()) {
                    User user = response.body();
                    mOnResultListener.onUser(user);
                } else {
                    mOnResultListener.onFail();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
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
        void onUser(User user);
        void onFail();
    }

    public interface Service {
        //@FormUrlEncoded
        @GET("users/{username}")//https://api.github.com/users/JakeWharton
        Call<User> create(@Path("username") String username);
    }
}