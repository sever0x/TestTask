package com.shdwraze.testtask.service.impl;

import com.shdwraze.testtask.config.UserProperties;
import com.shdwraze.testtask.exception.ValidationException;
import com.shdwraze.testtask.mapper.UserMapper;
import com.shdwraze.testtask.model.entity.UserEntity;
import com.shdwraze.testtask.model.request.UserRequest;
import com.shdwraze.testtask.repository.UserRepository;
import com.shdwraze.testtask.service.UserService;
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
        if (!isUserAdult(userRequest.birthday())) {
            throw new ValidationException(List.of("You must be over 18 years old!"));
        }
        return userRepository.save(userMapper.toUserEntity(userRequest));
    }

    private boolean isUserAdult(Date birthday) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -userProperties.getMinAge());
        Date eighteenYearsAgo = calendar.getTime();

        return birthday.before(eighteenYearsAgo);
    }
}
