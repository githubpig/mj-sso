package com.mj.sso.server.mapper;

import java.util.List;

import com.mj.sso.server.core.model.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


/**
 * 用户信息表 Mapper 接口
 *
 * @author piggy
 * @date 2021-11-07
 */
public interface SysUserMapper extends Mapper<SysUser> {
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

	@Select(" select * from sys_user where openid = #{openid} and unionid = #{unionid} ")
	SysUser findUserByWX(@Param("openid") String openid, @Param("unionid") String unionid);

	@Select(" select * from sys_user where login_name = #{loginName} and password = #{password} ")
	SysUser findUser(@Param("loginName") String username, @Param("password") String password);

	@Select(" select * from sys_user where login_name = #{loginName}")
	List<SysUser> findUserByUsername(@Param("loginName") String username);

	@Select(" select * from sys_user where user_id = #{userid}")
	SysUser findById(@Param("userid") Integer userid);
}
