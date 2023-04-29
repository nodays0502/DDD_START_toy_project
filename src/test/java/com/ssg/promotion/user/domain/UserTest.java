package com.ssg.promotion.user.domain;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.ssg.promotion.Exception.user.AlreadyUserStateIsResignException;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    void resignUserSuccessTest() {
        User user = User.withId(1L, "이수경",
            UserType.NORMAL, UserState.NORMAL);
        user.resign();
        assertEquals(1L, user.getId());
        assertEquals("이수경", user.getName());
        assertEquals(UserType.NORMAL, user.getType());
        assertEquals(UserState.RESIGN, user.getState());
    }

    @Test
    void ResignUserFailTest() {
        User user = User.withId(1L, "이수경",
            UserType.NORMAL, UserState.RESIGN);
        assertThrows(AlreadyUserStateIsResignException.class, () -> user.resign());
    }
}