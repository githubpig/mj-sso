package com.mj.sso.server.service;

import com.mj.sso.server.core.model.UserInfo;
import com.mj.sso.server.core.result.ReturnT;

public interface UserService {

    public ReturnT<UserInfo> findUser(String username, String password);

    ReturnT<UserInfo> findUserByWX(String openid, String unionid);
}
