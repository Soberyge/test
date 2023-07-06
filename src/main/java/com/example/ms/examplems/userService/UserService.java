package com.example.ms.examplems.userService;

import com.example.ms.examplems.model.request.UserDetailsRequestModel;
import com.example.ms.examplems.model.response.UserRest;

public interface UserService {
    UserRest createUser(UserDetailsRequestModel userDetailsRequestModel);
}
