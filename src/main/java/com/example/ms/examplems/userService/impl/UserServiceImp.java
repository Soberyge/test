package com.example.ms.examplems.userService.impl;

import com.example.ms.examplems.model.request.UserDetailsRequestModel;
import com.example.ms.examplems.model.response.UserRest;
import com.example.ms.examplems.shared.Utils;
import com.example.ms.examplems.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImp implements UserService {

    /**
     * map users.
     */
    private Map<String, UserRest> users;
    /**
     * utils.
     */
    private Utils utils;

    /**
     * constructor.
     */
    public UserServiceImp() {
    }

    /**
     *
     * @param utils
     */
    @Autowired
    public UserServiceImp(final Utils utils) {
        this.utils = utils;
    }

    /**
     *
     * @param userDetailsRequestModel
     * @return
     */
    @Override
    public UserRest createUser(
            final UserDetailsRequestModel userDetailsRequestModel) {

        UserRest user = new UserRest();
        user.setFirstName(userDetailsRequestModel.getFirstName());
        user.setLastName(userDetailsRequestModel.getLastName());
        user.setEmail(userDetailsRequestModel.getEmail());

        String userId = utils.generateUserId();
        user.setUserId(userId);

        if (users == null) {
            users = new HashMap<>();
        }
        users.put(userId, user);

        return user;
    }
}
