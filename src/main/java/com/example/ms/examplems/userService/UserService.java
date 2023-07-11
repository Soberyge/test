package com.example.ms.examplems.userService;

import com.example.ms.examplems.model.request.UserDetailsRequestModel;
import com.example.ms.examplems.model.response.UserRest;

public interface UserService {

    /**
     *
     * @param userDetailsRequestModel
     * @return userDetailsRequestModel
     */
    UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
}