package com.oohaweb.listuser.apis;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.oohaweb.listuser.model.User;
import com.oohaweb.listuser.model.UserDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {
    private static final String TAG = "WebService";
    private static final String GITHUB_URL = "https://api.github.com/";
    private static final int PAGE_SIZE = 100;

    private static WebService INSTANCE;

    private GithubRequest githubRequest;

    private WebService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        githubRequest = retrofit.create(GithubRequest.class);
    }

    public static WebService getInstance() {
        if (null == INSTANCE) {
            synchronized (WebService.class) {
                INSTANCE = new WebService();
            }
        }
        return INSTANCE;
    }

    public LiveData<UserDetail> getUserDetail(String userId) {
        final MutableLiveData<UserDetail> data = new MutableLiveData<>();
        githubRequest.getUserDetail(userId).enqueue(new Callback<UserDetail>() {
            @Override
            public void onResponse(Call<UserDetail> call, Response<UserDetail> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserDetail> call, Throwable t) {
                Log.e(TAG, Log.getStackTraceString(t));
            }
        });
        return data;
    }

    public LiveData<List<User>> getUsers() {
        final MutableLiveData<List<User>> data = new MutableLiveData<>();
        githubRequest.getUsers(PAGE_SIZE).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e(TAG, Log.getStackTraceString(t));
            }
        });
        return data;
    }
}
