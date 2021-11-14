package com.mj.sso.server.service.impl;

import java.util.List;

import com.mj.sso.core.util.StringUtils;
import com.mj.sso.server.config.WxConfig;
import com.mj.sso.server.core.model.CmsAttachment;
import com.mj.sso.server.core.model.SysSso;
import com.mj.sso.server.core.model.SysUser;
import com.mj.sso.server.mapper.CmsAttachmentMapper;
import com.mj.sso.server.mapper.SysSsoMapper;
import com.mj.sso.server.service.SysSsoService;

import com.mj.sso.server.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * sso集成应用表 服务实现类
 *
 * @author piggy
 * @date 2021-11-13
 */
@Service
public class SysSsoServiceImpl implements SysSsoService {

	private static final Logger LOG = LoggerFactory.getLogger(SysSsoServiceImpl.class);

	@Autowired
	private SysSsoMapper sysSsoMapper;

	@Autowired
	private CmsAttachmentMapper cmsAttachmentMapper;

	@Autowired
	private SysUserService sysUserService;

	/**
	 * 	添加
	 */
	@Override
	@Transactional
	public void saveSysSso(SysSso sysSso) {
		sysSsoMapper.saveSysSso(sysSso);
	}
	
	/**
	 * 	id查询
	 */
	@Override
	public SysSso findSysSsoById(SysSso sysSso) {
		SysSso ojb = sysSsoMapper.findSysSsoById(sysSso);
		return ojb;
	}
	@Override
	public List<SysSso> findSysSsoList(SysSso sysSso) {
		return sysSsoMapper.findSysSsoList(sysSso);
	}
	/**
	 * 	分页查询
	 */
	@Override
	public List<SysSso> findSysSsoList(SysSso sysSso,Integer userId) {
		List<SysSso> list = null;
		if(userId == 1){//超级管理员
			list = sysSsoMapper.findSysSsoList(sysSso);
		}else{
			SysUser dbUser = sysUserService.findById(userId);
			if(StringUtils.hasText(dbUser.getFromSys())){
				String[] split = dbUser.getFromSys().split(",");
				list = sysSsoMapper.findSysSsoListByIds(split);
			}
		}
		if(list == null || list.size() == 0 ){
			return list;
		}
		for (SysSso sso : list) {
			if(StringUtils.hasText(sso.getLogo())){
				String[] split = sso.getLogo().split(",");
				CmsAttachment attachment = cmsAttachmentMapper.selectAttachmentById(split[split.length-1]);
				String fileUrl = attachment.getFileUrl();
				if(fileUrl.startsWith("/")){
					fileUrl = WxConfig.MANAGE_URL + fileUrl;
				}else{
					fileUrl = WxConfig.MANAGE_URL + "/" + fileUrl;
				}
				sso.setImgUrl(fileUrl);
			}
		}
		return list;
	}
	
	/**
	 * 	修改
	 */
	@Override
	@Transactional
	public void updateSysSso(SysSso sysSso) {
		sysSsoMapper.updateSysSso(sysSso);
	}
	
	/**
	 * 	删除
	 */
	@Override
	@Transactional
	public void deleteSysSso(SysSso sysSso) {
		sysSsoMapper.deleteSysSso(sysSso);
	}

}
