package com.oohaweb.listuser.view_model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.oohaweb.listuser.model.User;
import com.oohaweb.listuser.model.UserDetail;
import com.oohaweb.listuser.repository.UserRepository;

import java.util.List;

public class UserViewModel extends ViewModel {
    private static UserViewModel INSTANCE;
    private UserViewModel() {
    }


    public static UserViewModel getInstance() {
        if (null == INSTANCE) {
            synchronized (UserViewModel.class) {
                INSTANCE = new UserViewModel();
            }
        }
        return INSTANCE;
    }

    public LiveData<UserDetail> getUserDetail(String userId) {
        return UserRepository.getInstance().getUser(userId);
    }

    public LiveData<List<User>> getUsers() {
        return UserRepository.getInstance().getUsers();
    }

}
