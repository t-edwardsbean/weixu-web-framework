package com.sean.service.writer;

import java.util.HashMap;
import java.util.Map;

import org.apache.avro.Schema.Field;
import org.apache.avro.specific.SpecificRecordBase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sean.persist.core.PersistContext;
import com.sean.persist.dictionary.Dictionary;
import com.sean.service.entity.ReturnParameterEntity;
import com.sean.service.entity.UseDicEntity;

/**
 * 单一avro实体数据类型fieldwriter
 * @author sean
 */
public class FieldWriterAvroEntity extends FieldWriter
{
	private static final FieldWriterAvroEntity instance = new FieldWriterAvroEntity();

	private FieldWriterAvroEntity()
	{
	}

	public static FieldWriterAvroEntity getInstance()
	{
		return instance;
	}

	@Override
	public void write(JSONObject data, Map<String, Object> retMap, ReturnParameterEntity param) throws Exception
	{
		String name = param.getName();
		SpecificRecordBase entity = (SpecificRecordBase) retMap.get(name);
		JSONObject entJson = null;

		if (entity != null)
		{
			entJson = new JSONObject();

			// 获取所有数据字典值
			Map<String, Object> dicVals = new HashMap<>();
			for (UseDicEntity dic : param.getDics())
			{
				Dictionary dictionary = PersistContext.CTX.getDictionary(dic.getDic());
				Object key = entity.get(entity.getSchema().getField(dic.getField()).pos());
				dictionary.getDicVal(key, dicVals);
			}

			// 开始生成json
			Object val = null;
			for (String field : param.getFields())
			{
				val = null;
				Field f = entity.getSchema().getField(field);
				if (f != null)
				{
					val = entity.get(f.pos());
				}
				if (val == null)
				{
					val = dicVals.get(field);
				}
				entJson.put(field, val);
			}
			for (String field : param.getJsonFields())
			{
				val = null;
				Field f = entity.getSchema().getField(field);
				if (f != null)
				{
					val = entity.get(f.pos());
				}
				if (val == null)
				{
					val = dicVals.get(field);
				}
				entJson.put(field, JSON.parse((String) val));
			}
		}
		data.put(name, entJson);
	}
}
