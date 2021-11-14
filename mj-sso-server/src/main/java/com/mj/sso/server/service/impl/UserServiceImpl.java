package com.mj.sso.server.service.impl;

import com.mj.sso.server.core.model.UserInfo;
import com.mj.sso.server.core.result.ReturnT;
import com.mj.sso.server.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static List<UserInfo> mockUserList = new ArrayList<>();
    static {
        for (int i = 0; i <5; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserid(1000+i);
            userInfo.setUsername("admin" + (i>0?String.valueOf(i):""));
            userInfo.setPassword("123456");
            mockUserList.add(userInfo);
        }
    }

    @Override
    public ReturnT<UserInfo> findUser(String username, String password) {

        if (username==null || username.trim().length()==0) {
            return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "Please input username.");
        }
        if (password==null || password.trim().length()==0) {
            return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "Please input password.");
        }

        // mock user
        for (UserInfo mockUser: mockUserList) {
            if (mockUser.getUsername().equals(username) && mockUser.getPassword().equals(password)) {
                return new ReturnT<UserInfo>(mockUser);
            }
        }

        return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "username or password is invalid.");
    }

    @Override
    public ReturnT<UserInfo> findUserByWX(String openid, String unionid) {
        if (openid==null || openid.trim().length()==0) {
            return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "微信扫码获取openid失败.");
        }
        if (unionid==null || unionid.trim().length()==0) {
            return new ReturnT<UserInfo>(ReturnT.FAIL_CODE, "微信扫码获取unionid失败.");
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setUserid(1000);
        userInfo.setUsername("admin");
        return new ReturnT<UserInfo>(userInfo);
    }


}
