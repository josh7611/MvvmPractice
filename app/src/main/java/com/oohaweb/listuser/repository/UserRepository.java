package com.oohaweb.listuser.repository;

import android.arch.lifecycle.LiveData;

import com.oohaweb.listuser.apis.WebService;
import com.oohaweb.listuser.model.User;
import com.oohaweb.listuser.model.UserDetail;

import java.util.HashMap;
import java.util.List;

public class UserRepository {
    private static UserRepository INSTANCE;

    private HashMap<String, LiveData<UserDetail>> userCache = new HashMap<>();

    private UserRepository() {
    }

    public static UserRepository getInstance() {
        if (null == INSTANCE) {
            synchronized (UserRepository.class) {
                INSTANCE = new UserRepository();
            }
        }
        return INSTANCE;
    }

    public LiveData<UserDetail> getUser(String userId) {
        LiveData<UserDetail> cached = userCache.get(userId);
        if (cached != null) {
            return cached;
        }

        LiveData<UserDetail> data = WebService.getInstance().getUserDetail(userId);
        userCache.put(userId, data);
        return data;
    }

    public LiveData<List<User>> getUsers() {
        return WebService.getInstance().getUsers();
    }
}
