package com.shdwraze.testtask.service.impl;

import com.shdwraze.testtask.config.UserProperties;
import com.shdwraze.testtask.exception.ValidationException;
import com.shdwraze.testtask.mapper.UserMapper;
import com.shdwraze.testtask.model.entity.UserEntity;
import com.shdwraze.testtask.model.request.UpdateUserRequest;
import com.shdwraze.testtask.model.request.UserRequest;
import com.shdwraze.testtask.model.response.UpdateUserResponse;
import com.shdwraze.testtask.repository.UserRepository;
import com.shdwraze.testtask.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserProperties userProperties;

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    @Override
    public UserEntity createUser(UserRequest userRequest) {
        checkBirthday(userRequest.birthday());
        return userRepository.save(userMapper.toUserEntity(userRequest));
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest userRequest, int id) {
        checkBirthday(userRequest.birthday());
        return userMapper.toUpdateUserResponse(userRepository.save(updateUserFields(
                userRepository.findById(id).orElseThrow(() ->
                        new EntityNotFoundException("User with id " + id + " not found!")), userRequest
        )));
    }

    private boolean isUserAdult(Date birthday) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -userProperties.getMinAge());
        Date eighteenYearsAgo = calendar.getTime();

        return birthday.before(eighteenYearsAgo);
    }

    private void checkBirthday(Date birthday) {
        if (!isUserAdult(birthday)) {
            throw new ValidationException(List.of("You must be over 18 years old!"));
        }
    }

    private UserEntity updateUserFields(UserEntity exUser, UpdateUserRequest curUser) {
        if (curUser.email() != null && !exUser.getEmail().equals(curUser.email())) {
            exUser.setEmail(curUser.email());
        }
        if (curUser.firstName() != null && !exUser.getFirstName().equals(curUser.firstName())) {
            exUser.setFirstName(curUser.firstName());
        }
        if (curUser.lastName() != null && !exUser.getLastName().equals(curUser.lastName())) {
            exUser.setLastName(curUser.lastName());
        }
        if (curUser.birthday() != null && !exUser.getBirthday().equals(curUser.birthday())) {
            exUser.setBirthday(curUser.birthday());
        }
        if (curUser.phoneNumber() != null && !exUser.getPhoneNumber().equals(curUser.phoneNumber())) {
            exUser.setPhoneNumber(curUser.phoneNumber());
        }
        if (curUser.address() != null && !exUser.getAddress().equals(curUser.address())) {
            exUser.setAddress(curUser.address());
        }

        return exUser;
    }
}
