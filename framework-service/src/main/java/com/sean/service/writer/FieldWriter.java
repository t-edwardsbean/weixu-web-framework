package com.sean.service.writer;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sean.service.entity.ReturnParameterEntity;

public abstract class FieldWriter
{
	public abstract void write(JSONObject data, Map<String, Object> retMap, ReturnParameterEntity param) throws Exception;
}
