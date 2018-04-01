package com.kotall.learn.shiro.controller;

import com.kotall.learn.shiro.auth.AccessToken;
import com.kotall.learn.shiro.bean.AuthToken;
import com.kotall.learn.shiro.dao.AuthUserDao;
import com.kotall.learn.shiro.dao.AuthTokenDao;
import com.kotall.learn.shiro.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : aracwong
 * @version : 1.0.0
 * @date : 2018/3/15 0015 下午 6:18
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    AuthUserDao userDao;

    @Autowired
    AuthTokenDao tokenDao;

    /**
     * 根据传递的 登录用户信息 生成 token
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestParam("username") String username, @RequestParam("password")String password) {
        AccessToken token = null;
        try {
            log.info("======username:{}, password:{}", username, password);
            Date expireTime = new Date(System.currentTimeMillis() + 1000 * 60 * 30);
            AuthToken authToken = this.tokenDao.save(new AuthToken(username, IdGenerator.generateValue(), expireTime));
            token = new AccessToken(authToken.getToken());
            SecurityUtils.getSubject().login(token);
        } catch (Exception e) {
            log.error("failed to login ", e);
        }
        return Result.ok().put("token", token.getToken());
    }
}
