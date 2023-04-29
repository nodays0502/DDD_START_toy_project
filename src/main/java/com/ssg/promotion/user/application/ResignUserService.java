package com.ssg.promotion.user.application;

import com.ssg.promotion.user.domain.User;
import com.ssg.promotion.Exception.user.NotFoundUserException;
import com.ssg.promotion.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ResignUserService {

    private final UserRepository userRepository;

    public void resign(long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundUserException());
        user.resign();
    }
}
