package com.shdwraze.testtask.model.request;

import com.shdwraze.testtask.validation.Email;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record UpdateUserRequest(

        @Email
        String email,

        String firstName,

        String lastName,

        @Temporal(TemporalType.DATE)
        Date birthday,

        String address,

        String phoneNumber
) {
}
