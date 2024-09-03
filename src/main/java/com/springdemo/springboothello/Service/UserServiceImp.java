package com.springdemo.springboothello.Service;

import com.springdemo.springboothello.Model.User;
import com.springdemo.springboothello.Exception.RequestException;
import com.springdemo.springboothello.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImp(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User CreateUser(User user) {
        User userGet = userMapper.getUserByUsername(user.getUsername());
        if (userGet != null) {
            throw new RequestException("CreateUser", "The username already exists");
        } else {
            userMapper.insertUser(user);
            return userMapper.getUserById(user.getId());
        }
    }

    @Override
    public void deleteUser(String username, String password) {
        try {
            int id = Login(username, password);
            userMapper.deleteUser(id);
        } catch (RequestException e) {
            throw new RequestException("deleteUser", e.getMsg());
        }
    }

    @Override
    public User getUserById(int id) {
        User user = userMapper.getUserById(id);
        if (user != null) {
            return user;
        } else {
            throw new RequestException("getUserById", "don't find user with id:'{}'" + id);
        }
    }

    @Override
    public User getUserByName(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user != null) {
            return user;
        } else {
            throw new RequestException("getByUsername", "don't find user with name: '{}'" + username);
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            getUserByName(user.getUsername());
            throw new RequestException("updateUser", "The username already exists");
        } catch (RequestException e) {
            if (!user.getUsername().trim().isEmpty()) {
                userMapper.updateUsername(user);
            }
            if (!user.getPassword().trim().isEmpty()) {
                userMapper.updatePassword(user);
            }
        }

    }

    @Override
    public int Login(String username, String password) {
        User user = userMapper.getUserByUsername(username);
        if (user == null) {
            throw new RequestException("Login", "don't find user with name: " + username);
        }
        if (user.getPassword().equals(password)) {
            return user.getId();
        } else {
            throw new RequestException("Login", "Username or password incorrect");
        }
    }


}
