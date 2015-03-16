package com.sean.service.writer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.avro.Schema.Field;
import org.apache.avro.specific.SpecificRecordBase;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sean.persist.core.PersistContext;
import com.sean.persist.dictionary.Dictionary;
import com.sean.service.entity.ReturnParameterEntity;
import com.sean.service.entity.UseDicEntity;

/**
 * 实体列表数据类型fieldwriter
 * @author sean
 */
@SuppressWarnings("unchecked")
public class FieldWriterAvroEntityList extends FieldWriter
{
	private static final FieldWriterAvroEntityList instance = new FieldWriterAvroEntityList();

	private FieldWriterAvroEntityList()
	{
	}

	public static FieldWriterAvroEntityList getInstance()
	{
		return instance;
	}

	@Override
	public void write(JSONObject data, Map<String, Object> retMap, ReturnParameterEntity param) throws Exception
	{
		String name = param.getName();
		List<SpecificRecordBase> entitys = (List<SpecificRecordBase>) retMap.get(name);
		JSONArray jsonlist = null;

		if (entitys != null)
		{
			jsonlist = new JSONArray(entitys.size());
			Map<String, Object> dicVals = null;
			Object val = null;
			JSONObject item = null;

			for (SpecificRecordBase entity : entitys)
			{
				item = new JSONObject();

				// 获取所有数据字典值
				dicVals = new HashMap<>();
				for (UseDicEntity dic : param.getDics())
				{
					Dictionary dictionary = PersistContext.CTX.getDictionary(dic.getDic());
					Object key = entity.get(entity.getSchema().getField(dic.getField()).pos());
					dictionary.getDicVal(key, dicVals);
				}

				// 开始生成json
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
					item.put(field, val);
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
					item.put(field, JSON.parse((String) val));
				}
				jsonlist.add(item);
			}
		}
		data.put(name, jsonlist);
	}
}
