package com.ssg.promotion.user.application;

import com.ssg.promotion.user.domain.User;
import com.ssg.promotion.user.ui.dto.RegisterUserRequest;
import com.ssg.promotion.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterUserService {

    private final UserRepository userRepository;

    public void register(RegisterUserRequest registerUserRequestDto) {
        User user = RegisterUserRequest.mapToUser(registerUserRequestDto);
        userRepository.save(user);
    }
}
