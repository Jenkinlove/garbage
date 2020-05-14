package com.xiong.admin.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 4446565928240245872L;

    private String username;

    private String password;

    private String permission;
}
