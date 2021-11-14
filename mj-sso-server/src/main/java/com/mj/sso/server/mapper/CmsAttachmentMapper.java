package com.mj.sso.server.mapper;

import com.mj.sso.server.core.model.CmsAttachment;
import com.sun.xml.internal.ws.api.message.Attachment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;



/**
 *  Mapper 接口
 *
 * @author piggy
 * @date 2021-11-13
 */
public interface CmsAttachmentMapper{

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

	@Select("select * from cms_attachment where attach_id = #{attachId}")
	CmsAttachment selectAttachmentById(@Param("attachId") String attachId);
}
