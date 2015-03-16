package com.sean.service.ext;

import java.util.Arrays;

import com.sean.service.core.Session;
import com.sean.service.entity.ParameterEntity;
import com.sean.service.enums.ParameterType;

/**
 * 枚举参数验证
 * @author Sean
 */
public class EnumChecker extends ParameterChecker
{
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
		String val = (String) value;
		for (String it : param.getEnumVals())
		{
			if (it.equals(val))
			{
				return true;
			}
		}
		session.invalid(nullableStr + " " + param.getDataType() + " parameter " + param.getName() + " error for value: " + val
				+ ", out of enum range " + Arrays.toString(param.getEnumVals()));
		return false;
	}

	@Override
	public Object getValue(String value)
	{
		return Integer.parseInt(value);
	}
}
