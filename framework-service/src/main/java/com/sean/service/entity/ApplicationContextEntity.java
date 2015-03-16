package com.sean.service.entity;

import com.sean.common.enums.AppServerType;

/**
 * 上下文实体
 * 
 * @author sean
 * 
 */
public class ApplicationContextEntity
{
	private AppServerType appServer;
	private Class<?> cls;

	public ApplicationContextEntity(AppServerType appServer, Class<?> cls)
	{
		this.appServer = appServer;
		this.cls = cls;
	}

	public AppServerType getAppServer()
	{
		return appServer;
	}

	public Class<?> getCls()
	{
		return cls;
	}

}
