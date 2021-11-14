package com.mj.sso.server.mapper;

import com.mj.sso.server.core.model.SysSso;

import java.util.List;



/**
 * sso集成应用表 Mapper 接口
 *
 * @author piggy
 * @date 2021-11-13
 */
public interface SysSsoMapper{

	/**
	 * 	添加
	 */
	void saveSysSso(SysSso sysSso);
	/**
	 * 	id查询
	 */
	SysSso findSysSsoById(SysSso sysSso);
	/**
	 * 	分页查询
	 */	
	List<SysSso> findSysSsoList(SysSso sysSso);
	/**
	 * 	修改
	 */	
	void updateSysSso(SysSso sysSso);
	/**
	 * 	删除
	 */	
	void deleteSysSso(SysSso sysSso);

    List<SysSso> findSysSsoListByIds(String[] split);
}
