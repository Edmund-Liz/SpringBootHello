package com.springdemo.springboothello;

import com.springdemo.springboothello.Model.User;
import com.springdemo.springboothello.Service.UserServiceImp;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class UserTest {

    @Autowired
    UserServiceImp userServiceImp;


    @BeforeAll
    public static void setUpBeforeClass() throws Exception {

    }


    @Test
    public void insertUserTest() {

        User user = new User("Liz", "123456");
        User user1=userServiceImp.CreateUser(user);

        System.out.println(user1.toString());

    }



}
