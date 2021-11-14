package com.mj.sso.server.service;

import com.mj.sso.server.core.model.CmsAttachment;
import com.sun.xml.internal.ws.api.message.Attachment;

import java.util.List;



/**
 *  服务类
 *
 * @author piggy
 * @date 2021-11-13
 */
public interface CmsAttachmentService {
	
	/**
	 * 	添加
	 */
	void saveCmsAttachment(CmsAttachment cmsAttachment);
	/**
	 * 	id查询
	 */
	CmsAttachment findCmsAttachmentById(CmsAttachment cmsAttachment);
	/**
	 * 	分页查询
	 */	
	List<CmsAttachment> findCmsAttachmentList(CmsAttachment cmsAttachment);
	/**
	 * 	修改
	 */	
	void updateCmsAttachment(CmsAttachment cmsAttachment);
	/**
	 * 	删除
	 */	
	void deleteCmsAttachment(CmsAttachment cmsAttachment);


	CmsAttachment selectAttachmentById(String attachId);
}
