package com.springdemo.springboothello.Controller;

import com.springdemo.springboothello.Model.User;
import com.springdemo.springboothello.Exception.RequestException;
import com.springdemo.springboothello.Service.UserServiceImp;
import com.springdemo.springboothello.Model.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImp userServiceImpl;

    @Autowired
    public UserController(UserServiceImp userServiceImp) {
        this.userServiceImpl = userServiceImp;
    }

    @PostMapping("/login")
    public ResponseResult<User> login(@RequestParam String username,@RequestParam String password) {
        int id=userServiceImpl.Login(username,password);
        User user=new User(id,username,password);
        return ResponseResult.success(user);
    }

    @PostMapping("/register")
    public ResponseResult<User> createUser(@RequestParam String username,@RequestParam String password) {
        User user = new User(username, password);
        User userReturn = userServiceImpl.CreateUser(user);
        return ResponseResult.success(userReturn);
    }

    @DeleteMapping("/delete")
    public ResponseResult<Boolean> deleteUser(@RequestParam String username,@RequestParam String password) {
        userServiceImpl.deleteUser(username,password);
        return ResponseResult.success("deleteUser:success");
    }

    @GetMapping("/get")
    public ResponseResult<User> getUser(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String username) {

        User user=null;
        if (!username.trim().isEmpty()) {
            user = userServiceImpl.getUserByName(username);
        } else if (id!=null) {
            user = userServiceImpl.getUserById(id);
        }

        if (user != null) {
            user.setPassword(null);
            return ResponseResult.success(user);
        } else {
            throw new RequestException("getUser","Either 'id' or 'username' must be provided.");
        }
    }

    @PutMapping("/update")
    public ResponseResult<String> updateUser
            (@RequestParam String username,@RequestParam String password
            ,@RequestParam(required = false) String new_name,
             @RequestParam(required = false) String new_password) {
        int id=userServiceImpl.Login(username,password);
        int t=0;
        if (new_name==null){
            new_name=username;
            t++;
        }
        if (new_password==null){
            new_password=password;
            t++;
        }
        if (t==2){
            throw new RequestException("updateUser","Either 'new_name' or 'new_password' must be provided.");
        }
        User user=new User(id,new_name,new_password);
        userServiceImpl.updateUser(user);
        return ResponseResult.success("updateUser:success");
    }



    @ExceptionHandler(RequestException.class)
    public ResponseResult<String> handleRequestException(RequestException e) {
        return ResponseResult.fail(e.getMessage());
    }
}
