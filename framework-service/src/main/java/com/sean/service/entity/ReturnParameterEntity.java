package com.sean.service.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.sean.service.enums.Format;
import com.sean.service.writer.FieldWriter;

/**
 * 返回参数实体
 * @author sean
 */
public class ReturnParameterEntity
{
	private String name;
	private Format format;
	private Class<?> entity;
	private String[] fields;
	private String[] jsonFields;
	private UseDicEntity[] dics;
	private String description;
	private FieldWriter fieldWriter;

	public ReturnParameterEntity(String name, Format format, Class<?> entity, String[] fields, UseDicEntity[] dics, String[] jsonFields,
			String description, FieldWriter fieldWriter)
	{
		this.name = name;
		this.format = format;
		this.entity = entity;
		this.jsonFields = jsonFields;
		this.dics = dics;
		this.description = description;
		this.fieldWriter = fieldWriter;

		// jsonFields覆盖field
		List<String> fieldsList = new LinkedList<>();
		Collections.addAll(fieldsList, fields);
		Set<String> jsonFieldSet = new HashSet<>();
		Collections.addAll(jsonFieldSet, jsonFields);
		List<String> targetFields = new LinkedList<>();
		for (String it : fields)
		{
			if (!jsonFieldSet.contains(it))
			{
				targetFields.add(it);
			}
		}
		this.fields = targetFields.toArray(new String[targetFields.size()]);
	}

	public String[] getJsonFields()
	{
		return jsonFields;
	}

	public String getName()
	{
		return name;
	}

	public Class<?> getEntity()
	{
		return entity;
	}

	public String[] getFields()
	{
		return fields;
	}

	public String getDescription()
	{
		return description;
	}

	public UseDicEntity[] getDics()
	{
		return dics;
	}

	public Format getFormat()
	{
		return format;
	}

	public FieldWriter getFieldWriter()
	{
		return fieldWriter;
	}

}
