package com.springdemo.springboothello.Service;

import com.springdemo.springboothello.Model.User;



public interface UserService {

    User CreateUser(User user);

    void deleteUser(String username,String password);

    User getUserById(int id);

    User getUserByName(String username);

    void updateUser(User user);

    int Login(String username, String password);

}
