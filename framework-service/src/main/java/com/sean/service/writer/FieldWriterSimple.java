package com.sean.service.writer;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sean.service.entity.ReturnParameterEntity;

/**
 * 直接输出, 不做任何转化
 * @author sean
 */
public class FieldWriterSimple extends FieldWriter
{
	private static final FieldWriterSimple instance = new FieldWriterSimple();

	private FieldWriterSimple()
	{
	}

	public static FieldWriterSimple getInstance()
	{
		return instance;
	}

	@Override
	public void write(JSONObject data, Map<String, Object> retMap, ReturnParameterEntity param)
	{
		String name = param.getName();
		Object val = retMap.get(name);
		data.put(name, val);
	}
}
