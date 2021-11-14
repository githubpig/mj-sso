package com.mj.sso.server.controller;


import com.mj.sso.core.entity.ReturnT;
import com.mj.sso.server.core.model.SysSso;
import com.mj.sso.server.service.SysSsoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * sso集成应用表 前端控制器
 *
 * @author piggy
 * @date 2021-11-13
 */
@Api(tags = "sso集成应用表管理接口")
@Controller
@RequestMapping("/sys/sysSso")
public class SysSsoController {
	
	private static final Logger LOG = LoggerFactory.getLogger(SysSsoController.class);

	@Autowired
	private SysSsoService sysSsoService;

	/**
	 * 	添加
	 */
	@ApiOperation("添加sso集成应用表")
	@ResponseBody
	@PostMapping("/saveSysSso")
	public ReturnT saveSysSso(@RequestBody SysSso sysSso) {
		sysSsoService.saveSysSso(sysSso);
		return ReturnT.SUCCESS;
	}
	
	/**
	 * 	id查询
	 */
	@ApiOperation("id查询sso集成应用表")
	@ResponseBody
	@PostMapping("/findSysSsoById")
	public ReturnT findSysSsoById(@RequestBody SysSso sysSso) {
		SysSso ojb = sysSsoService.findSysSsoById(sysSso);
		return new ReturnT(ojb);
	}
	
	/**
	 * 	分页查询
	 */
	@ApiOperation("分页查询sso集成应用表")
	@ResponseBody
	@PostMapping("/findSysSsoList")
	public ReturnT findSysSsoList(@RequestBody SysSso sysSso) {
		List<SysSso> list = sysSsoService.findSysSsoList(sysSso);
		return new ReturnT(list);
	}
	
	/**
	 * 	修改
	 */
	@ApiOperation("修改sso集成应用表")
	@ResponseBody
	@PostMapping("/updateSysSso")
	public ReturnT updateSysSso(@RequestBody SysSso sysSso) {
		sysSsoService.updateSysSso(sysSso);
		return ReturnT.SUCCESS;
	}
	
	/**
	 * 	删除
	 */
	@ApiOperation("删除sso集成应用表")
	@ResponseBody
	@PostMapping("/deleteSysSso")
	public ReturnT deleteSysSso(@RequestBody SysSso sysSso) {
		sysSsoService.deleteSysSso(sysSso);
		return ReturnT.SUCCESS;
	}

}

