package com.sean.service.enums;

/**
 * data type enum
 * @author sean
 */
public enum DataType
{
	/**
	 * int
	 */
	Int(0),
	/**
	 * long
	 */
	Long(1),
	/**
	 * float
	 */
	Float(2),
	/**
	 * string
	 */
	String(3),
	/**
	 * 枚举
	 */
	Enum(4),
	/**
	 * 文件
	 */
	File(5);

	private int code;

	DataType(int code)
	{
		this.code = code;
	}

	public int getValue()
	{
		return code;
	}
}
