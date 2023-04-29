package com.ssg.promotion.user.application;

import static org.junit.jupiter.api.Assertions.*;

import com.ssg.promotion.user.ui.dto.RegisterUserRequest;
import com.ssg.promotion.user.domain.User;
import com.ssg.promotion.user.domain.UserState;
import com.ssg.promotion.user.domain.UserType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @Autowired
    private RegisterUserService registerUserService;

    @PersistenceContext
    private EntityManager em;

    @Test
    void registerUserTest(){

        RegisterUserRequest registerUserRequestDto = new RegisterUserRequest("이수린", UserType.NORMAL, UserState.NORMAL);
        registerUserService.register(registerUserRequestDto);

        List<User> resultList = em.createQuery("select u from User u where u.name =: name", User.class)
            .setParameter("name", "이수린")
            .getResultList();

        assertEquals(1,resultList.size());

        assertEquals("이수린",resultList.get(0).getName());
        assertEquals(UserType.NORMAL,resultList.get(0).getType());
        assertEquals(UserState.NORMAL,resultList.get(0).getState());
    }
}