package com.sparta.managemyschedule.common;

import com.sparta.managemyschedule.entity.User;

public interface UserFixture {
    Long TEST_USER_ID = 1L;

    String TEST_USER_NAME= "username";
    String TEST_USER_PASSWORD ="password";
    String TEST_USER_EMAIL ="username@test.com";

    User TEST_USER = new User(TEST_USER_NAME,TEST_USER_PASSWORD,TEST_USER_EMAIL);

}
