package com.mj.sso.server.service;

import java.util.List;

import com.mj.sso.server.core.model.SysUser;
import com.mj.sso.server.core.result.ReturnT;


/**
 * 用户信息表 服务类
 *
 * @author piggy
 * @date 2021-11-07
 */
public interface SysUserService {
	
	/**
	 * 	添加
	 */
	void saveSysUser(SysUser sysUser);
	/**
	 * 	id查询
	 */
	SysUser findSysUserById(SysUser sysUser);
	/**
	 * 	分页查询
	 */
	List<SysUser> findSysUserList(SysUser sysUser);
	/**
	 * 	修改
	 */
	void updateSysUser(SysUser sysUser);
	/**
	 * 	删除
	 */
	void deleteSysUser(SysUser sysUser);


    ReturnT<SysUser> findUserByWX(String openid, String unionid);

	ReturnT<SysUser> findUser(String username, String password);

	ReturnT<SysUser> findUserByUsername(String username);

	SysUser findById(Integer userid);
}
