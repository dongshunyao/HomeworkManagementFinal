package com.bjtu.dsy.homeworkmanagement.util;

import com.bjtu.dsy.homeworkmanagement.model.User;
import org.springframework.util.DigestUtils;

public class TokenUtil {
    public static void setToken(User user) {
        String token = user.getName();
        token = DigestUtils.md5DigestAsHex(token.getBytes());
        token += user.getPassword();
        token = DigestUtils.md5DigestAsHex(token.getBytes());
        token += Math.random();
        token = DigestUtils.md5DigestAsHex(token.getBytes());
        user.setToken(token);
    }
}

