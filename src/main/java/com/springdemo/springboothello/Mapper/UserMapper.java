package com.springdemo.springboothello.Mapper;

import com.springdemo.springboothello.Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {


//    List<User> findAll();


    void insertUser(User user);


    void deleteUser(@Param("id") int id);


    User getUserById(int id);


    User getUserByUsername(String username);


    void updateUsername(User user);


    void updatePassword(User user);

}
