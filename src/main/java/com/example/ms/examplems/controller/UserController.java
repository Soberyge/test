package com.example.ms.examplems.controller;

import com.example.ms.examplems.model.request.UpdateUserDetailsModel;
import com.example.ms.examplems.model.request.UserDetailsRequestModel;
import com.example.ms.examplems.model.response.UserRest;
import com.example.ms.examplems.userService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {

    Map<String, UserRest> users;

    @Autowired
    UserService userService;

    @GetMapping
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit") int limit){
        return "Users called with page: " + page + " and limit: " + limit;
    }

    @GetMapping(path = "{id}",  produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> getUser(@PathVariable String id){

        String firstName = null;

        if(users.containsKey(id)){
            return  new ResponseEntity<>(users.get(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping (consumes = {
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetailsRequestModel){

        UserRest user = userService.createUser(userDetailsRequestModel);
        return new ResponseEntity<UserRest>(user, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}", consumes = {
            MediaType.APPLICATION_JSON_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    public UserRest updateUser(@PathVariable String id, @Valid @RequestBody UpdateUserDetailsModel userDetailsRequestModel){

        UserRest userDetails =  users.get(id);
        userDetails.setLastName(userDetailsRequestModel.getLastName());
        userDetails.setFirstName(userDetailsRequestModel.getFirstName());

        users.put(id,userDetails);

        return userDetails;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id){
        users.remove(id);

        return ResponseEntity.noContent().build();
    }


}
