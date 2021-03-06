package com.sean.service.entity;

import com.sean.service.enums.DataType;
import com.sean.service.enums.ParameterType;

/**
 * Parameter实体
 * @author sean
 *
 */
public class ParameterEntity
{
	private String name;
	private DataType dataType;
	private ParameterType type;
	private String regex;
	private long length;
	private String[] enumVals;
	private String description;
	private String errormsg;

	public ParameterEntity(String name, DataType dataType, ParameterType type, String regex, int length, String[] enumVals, String description,
			String errormsg)
	{
		this.name = name;
		this.dataType = dataType;
		this.type = type;
		this.regex = regex;
		this.length = length;
		this.enumVals = enumVals;
		this.description = description;
		this.errormsg = errormsg;
	}

	public String getName()
	{
		return name;
	}

	public DataType getDataType()
	{
		return dataType;
	}

	public ParameterType getType()
	{
		return type;
	}

	public String getRegex()
	{
		return regex;
	}

	public String getDescription()
	{
		return description;
	}

	public long getLength()
	{
		return length;
	}

	public String getErrormsg()
	{
		return errormsg;
	}

	public String[] getEnumVals()
	{
		return enumVals;
	}

}
