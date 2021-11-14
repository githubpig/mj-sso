package com.mj.sso.server.service.impl;

import java.util.List;


import com.mj.sso.server.core.model.CmsAttachment;
import com.mj.sso.server.mapper.CmsAttachmentMapper;
import com.mj.sso.server.service.CmsAttachmentService;
import com.sun.xml.internal.ws.api.message.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author piggy
 * @date 2021-11-13
 */
@Service
public class CmsAttachmentServiceImpl implements CmsAttachmentService {

	private static final Logger LOG = LoggerFactory.getLogger(CmsAttachmentServiceImpl.class);

	@Autowired
	private CmsAttachmentMapper cmsAttachmentMapper;

	/**
	 * 	添加
	 */
	@Override
	@Transactional
	public void saveCmsAttachment(CmsAttachment cmsAttachment) {
		cmsAttachmentMapper.saveCmsAttachment(cmsAttachment);
	}
	
	/**
	 * 	id查询
	 */
	@Override
	public CmsAttachment findCmsAttachmentById(CmsAttachment cmsAttachment) {
		CmsAttachment ojb = cmsAttachmentMapper.findCmsAttachmentById(cmsAttachment);
		return ojb;
	}
	
	/**
	 * 	分页查询
	 */
	@Override
	public List<CmsAttachment> findCmsAttachmentList(CmsAttachment cmsAttachment) {
		List<CmsAttachment> list = cmsAttachmentMapper.findCmsAttachmentList(cmsAttachment);
		return list;
	}
	
	/**
	 * 	修改
	 */
	@Override
	@Transactional
	public void updateCmsAttachment(CmsAttachment cmsAttachment) {
		cmsAttachmentMapper.updateCmsAttachment(cmsAttachment);
	}
	
	/**
	 * 	删除
	 */
	@Override
	@Transactional
	public void deleteCmsAttachment(CmsAttachment cmsAttachment) {
		cmsAttachmentMapper.deleteCmsAttachment(cmsAttachment);
	}

	@Override
	public CmsAttachment selectAttachmentById(String attachId) {
		return cmsAttachmentMapper.selectAttachmentById(attachId);
	}

}
