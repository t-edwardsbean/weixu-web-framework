package com.sean.service.core;

import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sean.service.entity.ActionEntity;
import com.sean.service.entity.ReturnParameterEntity;
import com.sean.service.enums.ResultState;

/**
 * json转换工具
 * @author sean
 */
public final class JsonWriter
{
	public static String toJson(Session session, ActionEntity action) throws Exception
	{
		JSONObject json = new JSONObject();

		if (session.getState() == ResultState.Success)
		{
			JSONObject data = new JSONObject();
			json.put("state", "Success");
			json.put("data", data);

			Map<String, Object> retMap = session.getReturnAttributeMap();
			for (ReturnParameterEntity param : action.getReturnParams())
			{
				param.getFieldWriter().write(data, retMap, param);
			}
		}
		else if (session.getState() == ResultState.BusinessException)
		{
			json.put("state", session.getState());
			json.put("msg", session.getMsg());
			json.put("code", session.getCode());
		}
		else
		{
			json.put("state", session.getState());
			json.put("msg", session.getMsg());
		}
		return json.toJSONString();
	}
}
