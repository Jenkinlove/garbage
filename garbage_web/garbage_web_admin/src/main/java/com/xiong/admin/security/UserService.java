package com.xiong.admin.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("用户名称为空");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("admin");
        userInfo.setPassword("123456");
        userInfo.setPermission("ADMIN");

        if (!userInfo.getUsername().equals(username)) {
            throw new UsernameNotFoundException("账户不存在");
        }

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(userInfo.getPermission());
        return new User(userInfo.getUsername(), passwordEncoder.encode(userInfo.getPassword()),
                Collections.singleton(simpleGrantedAuthority));
    }
}
