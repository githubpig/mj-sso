package com.mj.sso.server.service.impl;

import java.util.List;

import com.mj.sso.server.core.model.SysUser;
import com.mj.sso.server.core.result.ReturnT;
import com.mj.sso.server.mapper.SysUserMapper;
import com.mj.sso.server.service.SysUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * 用户信息表 服务实现类
 *
 * @author piggy
 * @date 2021-11-07
 */
@Service
public class SysUserServiceImpl implements SysUserService {

	private static final Logger LOG = LoggerFactory.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserMapper sysUserMapper;

	/**
	 * 	添加
	 */
	@Override
	@Transactional
	public void saveSysUser(SysUser sysUser) {
		sysUserMapper.saveSysUser(sysUser);
	}
	
	/**
	 * 	id查询
	 */
	@Override
	public SysUser findSysUserById(SysUser sysUser) {
		SysUser ojb = sysUserMapper.findSysUserById(sysUser);
		return ojb;
	}
	
	/**
	 * 	分页查询
	 */
	@Override
	public List<SysUser> findSysUserList(SysUser sysUser) {
		List<SysUser> list = sysUserMapper.findSysUserList(sysUser);
		return list;
	}
	
	/**
	 * 	修改
	 */
	@Override
	@Transactional
	public void updateSysUser(SysUser sysUser) {
		sysUserMapper.updateSysUser(sysUser);
	}
	
	/**
	 * 	删除
	 */
	@Override
	@Transactional
	public void deleteSysUser(SysUser sysUser) {
		sysUserMapper.deleteSysUser(sysUser);
	}

	@Override
	public ReturnT<SysUser> findUserByWX(String openid, String unionid) {
		SysUser user = sysUserMapper.findUserByWX(openid, unionid);
		if(user == null){
			return new ReturnT<SysUser>(ReturnT.FAIL_CODE, "用户不存在");
		}else{
			return new ReturnT<SysUser>(user);
		}
	}

	@Override
	public ReturnT<SysUser> findUser(String username, String password) {
		SysUser user = sysUserMapper.findUser(username,password);
		if(user == null){
			return new ReturnT<SysUser>(ReturnT.FAIL_CODE, "用户不存在");
		}else{
			return new ReturnT<SysUser>(user);
		}
	}

	@Override
	public ReturnT<SysUser> findUserByUsername(String username) {
		List<SysUser> users = sysUserMapper.findUserByUsername(username);
		if(CollectionUtils.isEmpty(users)){
			return new ReturnT<SysUser>(ReturnT.FAIL_CODE, "用户不存在");
		}else{
			if(users.size()>1){
				return new ReturnT<SysUser>(ReturnT.FAIL_CODE, "用户名重复");
			}else{
				return new ReturnT<SysUser>(users.get(0));
			}
		}
	}

	@Override
	public SysUser findById(Integer userid) {
		return sysUserMapper.findById(userid);
	}
}
