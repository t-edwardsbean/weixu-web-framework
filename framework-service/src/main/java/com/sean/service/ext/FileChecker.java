package com.sean.service.ext;

import javax.servlet.http.Part;

import com.sean.service.core.Session;
import com.sean.service.entity.ParameterEntity;
import com.sean.service.enums.ParameterType;

/**
 * 字符串参数验证
 * @author Sean
 *
 */
public class FileChecker extends ParameterChecker
{
	@Override
	public boolean checkParameter(Session session, ParameterEntity param, Object value, String nullableStr)
	{
		Part part = (Part) value;
		if (param.getLength() != 0)
		{
			if (part.getSize() > param.getLength())
			{
				session.invalid(nullableStr + " " + param.getDataType() + " parameter " + param.getName() + " error for value: " + value
						+ ", file bytes greater " + param.getLength());
				return false;
			}
		}
		return true;
	}

	@Override
	public Object getValue(String value)
	{
		throw new UnsupportedOperationException("实体不能包含文件类型字段");
	}

	@Override
	public Object getParameter(Session session, ParameterEntity param)
	{
		if (param.getType() == ParameterType.Single)
		{
			return session.getFile(param.getName());
		}
		else
		{
			throw new UnsupportedOperationException("文件参数暂不支持批量");
		}

	}
}
