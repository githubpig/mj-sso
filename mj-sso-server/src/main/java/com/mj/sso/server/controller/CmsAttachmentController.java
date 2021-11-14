package com.mj.sso.server.controller;

import java.util.List;

import com.mj.sso.core.entity.ReturnT;
import com.mj.sso.server.core.model.CmsAttachment;
import com.mj.sso.server.service.CmsAttachmentService;
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
 *  前端控制器
 *
 * @author piggy
 * @date 2021-11-13
 */
@Api(tags = "管理接口")
@RestController
@RequestMapping("/sys/cmsAttachment")
public class CmsAttachmentController {
	
	private static final Logger LOG = LoggerFactory.getLogger(CmsAttachmentController.class);

	@Autowired
	private CmsAttachmentService cmsAttachmentService;

	/**
	 * 	添加
	 */
	@ApiOperation("添加")
	@PostMapping("/saveCmsAttachment")
	public ReturnT saveCmsAttachment(@RequestBody CmsAttachment cmsAttachment) {
		cmsAttachmentService.saveCmsAttachment(cmsAttachment);
		return ReturnT.SUCCESS;
	}
	
	/**
	 * 	id查询
	 */
	@ApiOperation("id查询")
	@PostMapping("/findCmsAttachmentById")
	public ReturnT findCmsAttachmentById(@RequestBody CmsAttachment cmsAttachment) {
		CmsAttachment ojb = cmsAttachmentService.findCmsAttachmentById(cmsAttachment);
		return new ReturnT(ojb);
	}
	
	/**
	 * 	分页查询
	 */
	@ApiOperation("分页查询")
	@PostMapping("/findCmsAttachmentList")
	public ReturnT findCmsAttachmentList(@RequestBody CmsAttachment cmsAttachment) {
		List<CmsAttachment> list = cmsAttachmentService.findCmsAttachmentList(cmsAttachment);
		return new ReturnT(list);
	}
	
	/**
	 * 	修改
	 */
	@ApiOperation("修改")
	@PostMapping("/updateCmsAttachment")
	public ReturnT updateCmsAttachment(@RequestBody CmsAttachment cmsAttachment) {
		cmsAttachmentService.updateCmsAttachment(cmsAttachment);
		return ReturnT.SUCCESS;
	}
	
	/**
	 * 	删除
	 */
	@ApiOperation("删除")
	@PostMapping("/deleteCmsAttachment")
	public ReturnT deleteCmsAttachment(@RequestBody CmsAttachment cmsAttachment) {
		cmsAttachmentService.deleteCmsAttachment(cmsAttachment);
		return ReturnT.SUCCESS;
	}
	


}

