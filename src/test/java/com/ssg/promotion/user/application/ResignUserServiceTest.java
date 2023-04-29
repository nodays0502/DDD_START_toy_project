package com.ssg.promotion.user.application;

import static org.junit.jupiter.api.Assertions.*;

import com.ssg.promotion.user.domain.User;
import com.ssg.promotion.user.domain.UserState;
import com.ssg.promotion.user.domain.UserType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

class ResignUserServiceTest {

    @Autowired
    private ResignUserService resignUserService;

    @PersistenceContext
    private EntityManager em;

    @Test
    @Transactional
    void registerUserTest() {
        User user = User.withOutId("이수진", UserType.NORMAL, UserState.NORMAL);
        em.persist(user);
        User singleResult = em.createQuery("select u from User u where u.name =: name", User.class)
            .setParameter("name", "이수진")
            .getSingleResult();

        resignUserService.resign(singleResult.getId());

        em.flush();
        em.clear();

        singleResult = em.createQuery("select u from User u where u.name =: name", User.class)
            .setParameter("name", "이수진")
            .getSingleResult();

        assertEquals("이수진",singleResult.getName());
        assertEquals(UserType.NORMAL,singleResult.getType());
        assertEquals(UserState.RESIGN,singleResult.getState());

    }
}