package com.sean.service.writer;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sean.persist.core.Entity;
import com.sean.persist.core.PersistContext;
import com.sean.persist.dictionary.Dictionary;
import com.sean.service.entity.ReturnParameterEntity;
import com.sean.service.entity.UseDicEntity;

/**
 * 单一实体数据类型fieldwriter
 * @author sean
 */
public class FieldWriterEntity extends FieldWriter
{
	private static final FieldWriterEntity instance = new FieldWriterEntity();

	private FieldWriterEntity()
	{
	}

	public static FieldWriterEntity getInstance()
	{
		return instance;
	}

	@Override
	public void write(JSONObject data, Map<String, Object> retMap, ReturnParameterEntity param) throws Exception
	{
		String name = param.getName();
		Entity entity = (Entity) retMap.get(name);
		JSONObject entJson = null;

		if (entity != null)
		{
			entJson = new JSONObject();
			Map<String, Object> values = entity.getValues();

			// 获取所有数据字典值
			for (UseDicEntity dic : param.getDics())
			{
				Dictionary dictionary = PersistContext.CTX.getDictionary(dic.getDic());
				Object key = values.get(dic.getField());
				if (key != null)
				{
					dictionary.getDicVal(key, values);
				}
			}

			// 开始生成json
			for (String field : param.getFields())
			{
				entJson.put(field, values.get(field));
			}
			for (String field : param.getJsonFields())
			{
				entJson.put(field, JSON.parse((String) values.get(field)));
			}
		}
		data.put(name, entJson);
	}
}
