package com.example.service;

import com.example.domain.Users;

/**
 * @Author changxizhao
 * @Date 2020/6/15 14:04
 * @Description
 */
public interface UserService {

    public Users selectUserByUserName(String userName);

}
