package com.sean.service.ext;

import java.util.regex.Pattern;

import com.sean.service.core.Session;
import com.sean.service.entity.ParameterEntity;
import com.sean.service.enums.ParameterType;

/**
 * 整形参数验证
 * @author Sean
 */
public class IntegerChecker extends ParameterChecker
{
	private final Pattern pattern = Pattern.compile("[0-9]+");

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

	@Override
	public boolean checkParameter(Session session, ParameterEntity param, Object value, String nullableStr)
	{
		boolean rs = pattern.matcher((String) value).matches();
		if (!rs)
		{
			session.invalid(nullableStr + " " + param.getDataType() + " parameter " + param.getName() + " error for value: " + value);
		}
		return rs;
	}

	@Override
	public Object getValue(String value)
	{
		return Integer.parseInt(value);
	}

}
