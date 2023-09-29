package com.shdwraze.testtask.model.response;

import java.util.Date;

public record UpdateUserResponse(

        int id,

        String email,

        String firstName,

        String lastName,

        Date birthday,

        String address,

        String phoneNumber
) {
}
