package com.example.service.impl;

import com.example.dao.UserDAO;
import com.example.domain.Users;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author changxizhao
 * @Date 2020/6/15 14:04
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public Users selectUserByUserName(String userName) {
        Users user = new Users();
        user.setUserName(userName);
        List<Users> users = userDAO.findAll(Example.of(user));
        return users.isEmpty() ? null : users.get(0);
    }
}
