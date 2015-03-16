package com.sean.service.ext;

import com.sean.service.core.Session;
import com.sean.service.entity.ParameterEntity;
import com.sean.service.enums.ParameterType;

/**
 * 参数验证接口
 * @author Sean
 */
public abstract class ParameterChecker
{
	/**
	 * 验证参数
	 * @param session
	 * @param param
	 * @param nullable
	 * @return
	 */
	public boolean check(Session session, ParameterEntity param, boolean nullable)
	{
		Object val = this.getParameter(session, param);
		if (!nullable)
		{
			if (val != null)
			{
				return this.checkInterval(val, session, param, "not-null");
			}
			session.invalid("not-null " + param.getDataType() + " parameter " + param.getName() + " error for value: null");
			return false;
		}
		else
		{
			if (val != null)
			{
				return this.checkInterval(val, session, param, "nullable");
			}
			return true;
		}
	}

	private boolean checkInterval(Object value, Session session, ParameterEntity param, String nullableStr)
	{
		if (param.getType() == ParameterType.Single)
		{
			return this.checkParameter(session, param, value, nullableStr);
		}
		else
		{
			for (Object it : (Object[]) value)
			{
				if (!this.checkParameter(session, param, it, nullableStr))
				{
					return false;
				}
			}
			return true;
		}
	}

	/**
	 * 获取参数
	 * @param session
	 * @param param
	 * @return
	 */
	public abstract Object getParameter(Session session, ParameterEntity param);

	/**
	 * 验证参数
	 * @param val						参数值
	 * @param param						参数定义
	 * @return
	 */
	public abstract boolean checkParameter(Session session, ParameterEntity param, Object value, String nullableStr);

	/**
	 * 读取参数值, 传入字符串, 转成对应类型
	 * @param value
	 * @return
	 */
	public abstract Object getValue(String value);
}
