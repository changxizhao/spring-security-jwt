package com.example.config.security.service;

import com.example.config.LoginUser;
import com.example.domain.Users;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author changxizhao
 * @Date 2020/6/15 14:18
 * @Description
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userService.selectUserByUserName(username);
        if(user == null) {
            throw new UsernameNotFoundException("用户名" + username + "不存在");
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRoles()));
        loginUser.setUser(user);
        return loginUser;
    }
}
