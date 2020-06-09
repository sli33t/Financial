package cn.itcast.user.controller;

import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login/{username}/{password}")
    public List<User> login(@PathVariable("username") String username, @PathVariable("password") String password){
        return userService.getUser();
    }
}
