package com.example.ms.examplems.model.request;

import jakarta.validation.constraints.NotNull;

public class UpdateUserDetailsModel {
    @NotNull(message = "first name cannot be null")
    private String firstName;
    @NotNull(message = "last name cannot be null")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
