package com.mj.sso.server.controller;

import java.util.List;

import com.mj.sso.core.entity.ReturnT;
import com.mj.sso.server.core.model.SysUser;
import com.mj.sso.server.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 用户信息表 前端控制器
 *
 * @author piggy
 * @date 2021-11-07
 */
@Api(tags = "用户信息表管理接口")
@RestController
@RequestMapping("/user/sysUser")
public class SysUserController {
	
	private static final Logger log = LoggerFactory.getLogger(SysUserController.class);

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 	添加
	 */
	@ApiOperation("添加用户信息表")
	@PostMapping("/saveSysUser")
	public ReturnT saveSysUser(@RequestBody SysUser sysUser) {
		sysUserService.saveSysUser(sysUser);
		return ReturnT.SUCCESS;
	}
	
	/**
	 * 	id查询
	 */
	@ApiOperation("id查询用户信息表")
	@PostMapping("/findSysUserById")
	public ReturnT findSysUserById(@RequestBody SysUser sysUser) {
		SysUser ojb = sysUserService.findSysUserById(sysUser);
		return new ReturnT(ojb);
	}
	
	/**
	 * 	分页查询
	 */
	@ApiOperation("分页查询用户信息表")
	@PostMapping("/findSysUserList")
	public ReturnT findSysUserList(@RequestBody SysUser sysUser) {
		List<SysUser> list = sysUserService.findSysUserList(sysUser);
		return new ReturnT(list);
	}
	
	/**
	 * 	修改
	 */
	@ApiOperation("修改用户信息表")
	@PostMapping("/updateSysUser")
	public ReturnT updateSysUser(@RequestBody SysUser sysUser) {
		sysUserService.updateSysUser(sysUser);
		return ReturnT.SUCCESS;
	}
	
	/**
	 * 	删除
	 */
	@ApiOperation("删除用户信息表")
	@PostMapping("/deleteSysUser")
	public ReturnT deleteSysUser(@RequestBody SysUser sysUser) {
		sysUserService.deleteSysUser(sysUser);
		return ReturnT.SUCCESS;
	}

}

