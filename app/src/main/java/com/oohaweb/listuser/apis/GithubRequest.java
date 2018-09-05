package com.oohaweb.listuser.apis;

import com.oohaweb.listuser.model.User;
import com.oohaweb.listuser.model.UserDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GithubRequest {

    @GET("/users/{user}")
    Call<UserDetail> getUserDetail(@Path("user") String userId);

    @GET("/users")
    Call<List<User>> getUsers(@Query("per_page") int pageSize);
}
