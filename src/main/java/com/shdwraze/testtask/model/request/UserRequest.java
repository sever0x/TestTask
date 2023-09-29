package com.shdwraze.testtask.model.request;

import com.shdwraze.testtask.validation.Email;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Date;

@Builder
public record UserRequest(

        @Email
        String email,

        @NotNull(message = "{NotNull.userRequest.firstName}")
        String firstName,

        @NotNull(message = "{NotNull.userRequest.lastName}")
        String lastName,

        @NotNull(message = "{NotNull.userRequest.birthday}")
        @Temporal(TemporalType.DATE)
        Date birthday,

        String address,

        String phoneNumber
) {
}
