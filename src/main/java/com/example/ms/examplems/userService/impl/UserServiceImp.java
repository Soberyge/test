package com.example.ms.examplems.userService.impl;

import com.example.ms.examplems.model.request.UserDetailsRequestModel;
import com.example.ms.examplems.model.response.UserRest;
import com.example.ms.examplems.shared.Utils;
import com.example.ms.examplems.userService.UserService;
import jdk.jshell.execution.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImp implements UserService {

    Map<String, UserRest> users;
    Utils utils;

    public UserServiceImp(){

    }

    @Autowired
    public UserServiceImp(Utils utils) {
        this.utils = utils;
    }

    @Override
    public UserRest createUser(UserDetailsRequestModel userDetailsRequestModel) {

        UserRest user = new UserRest();
        user.setFirstName(userDetailsRequestModel.getFirstName());
        user.setLastName(userDetailsRequestModel.getLastName());
        user.setEmail(userDetailsRequestModel.getEmail());

        String userId = utils.generateUserId();
        user.setUserId(userId);

        if(users == null) users = new HashMap<>();
        users.put(userId, user);

        return user;
    }
}
