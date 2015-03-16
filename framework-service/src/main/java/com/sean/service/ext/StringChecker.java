package com.sean.service.ext;

import java.util.regex.Pattern;

import com.sean.service.core.Session;
import com.sean.service.entity.ParameterEntity;
import com.sean.service.enums.ParameterType;

/**
 * 字符串参数验证
 * @author Sean
 */
public class StringChecker extends ParameterChecker
{
	@Override
	public boolean checkParameter(Session session, ParameterEntity param, Object value, String nullableStr)
	{
		String str = (String) value;
		// 如果有长度限制
		if (param.getLength() != 0)
		{
			if (str.length() > param.getLength())
			{
				session.invalid(nullableStr + " " + param.getDataType() + " parameter " + param.getName() + " error for value: " + value
						+ ", length greater " + param.getLength());
				return false;
			}
		}
		// 如果有配置正则表达式
		if (!param.getRegex().isEmpty())
		{
			Pattern pattern = Pattern.compile(param.getRegex());
			boolean rs = pattern.matcher(str).matches();
			if (!rs)
			{
				session.invalid(nullableStr + " " + param.getDataType() + " parameter " + param.getName() + " error for value: " + value
						+ ", not match regex " + param.getRegex());
				return false;
			}
		}
		return true;
	}

	@Override
	public Object getValue(String value)
	{
		return value;
	}

	@Override
	public Object getParameter(Session session, ParameterEntity param)
	{
		if (param.getType() == ParameterType.Single)
		{
			return session.getParameter(param.getName());
		}
		else
		{
			return session.getParameters(param.getName());
		}
	}
}
