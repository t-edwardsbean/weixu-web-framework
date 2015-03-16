package com.sean.service.writer;

import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sean.persist.core.Entity;
import com.sean.persist.core.PersistContext;
import com.sean.persist.dictionary.Dictionary;
import com.sean.service.entity.ReturnParameterEntity;
import com.sean.service.entity.UseDicEntity;

/**
 * 实体列表数据类型fieldwriter
 * @author sean
 */
@SuppressWarnings("unchecked")
public class FieldWriterEntityList extends FieldWriter
{
	private static final FieldWriterEntityList instance = new FieldWriterEntityList();

	private FieldWriterEntityList()
	{
	}

	public static FieldWriterEntityList getInstance()
	{
		return instance;
	}

	@Override
	public void write(JSONObject data, Map<String, Object> retMap, ReturnParameterEntity param) throws Exception
	{
		String name = param.getName();
		List<Entity> entitys = (List<Entity>) retMap.get(name);
		JSONArray jsonlist = null;

		if (entitys != null)
		{
			jsonlist = new JSONArray(entitys.size());
			Map<String, Object> values = null;
			JSONObject item = null;

			for (Entity entity : entitys)
			{
				item = new JSONObject();
				values = entity.getValues();

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
					item.put(field, values.get(field));
				}
				for (String field : param.getJsonFields())
				{
					item.put(field, JSON.parse((String) values.get(field)));
				}
				jsonlist.add(item);
			}
		}
		data.put(name, jsonlist);
	}
}
