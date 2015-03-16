package com.sean.service.core;

import javax.servlet.ServletContextEvent;

import com.sean.config.core.Config;

/**
 * Servlet容器监听器，应用程序的执行入口
 * @author Sean
 */
public final class ServletContextListener extends AbstractLauncher implements javax.servlet.ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent ctxe)
	{
		try
		{
			// 读取配置文件
			Config.readConfiguration();

			// 构建框架
			this.build(ctxe);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent ctxe)
	{
		// 执行销毁监听器
		this.destroyedListrner(ctxe);
	}

	@Override
	protected ApplicationContextBuilder getBuildContext()
	{
		return new ApplicationContextBuilder();
	}
}
