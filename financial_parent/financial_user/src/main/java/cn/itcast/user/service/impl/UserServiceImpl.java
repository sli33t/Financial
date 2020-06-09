package cn.itcast.user.service.impl;

import cn.itcast.user.dao.UserDao;
import cn.itcast.user.domain.User;
import cn.itcast.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getUser() {
        return userDao.selectByMap(new HashMap<>());
    }
}