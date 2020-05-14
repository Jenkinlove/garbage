package com.xiong.admin.controller;

import com.xiong.admin.security.UserInfo;
import com.xiong.common.utils.Response;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/loginName")
    public Response<UserInfo> loginName() {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            UserInfo userAuthenticationInfo = new UserInfo();
            userAuthenticationInfo.setUsername(name);
            return Response.ok(userAuthenticationInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail("login.find.user.authentication.fail");
        }

    }
}
