package com.sean.example.constant;

import com.sean.example.dic.UserDic;
import com.sean.example.entity.ExecuteEntity;
import com.sean.example.entity.ReportEntity;
import com.sean.example.entity.UserEntity;
import com.sean.service.annotation.FieldsConfig;
import com.sean.service.annotation.ReturnParameterConfig;
import com.sean.service.annotation.ReturnParameterProviderConfig;
import com.sean.service.annotation.UseDicConfig;
import com.sean.service.enums.Format;

@ReturnParameterProviderConfig(module = M.class, descr = "")
public class R
{
	@ReturnParameterConfig(format = Format.Numeric, descr = "报表ID")
	public static final String reportId = "reportId";

	@ReturnParameterConfig(format = Format.String, descr = "sid")
	public static final String sid = "sid";

	@ReturnParameterConfig(format = Format.String, descr = "encryptKey")
	public static final String encryptKey = "encryptKey";

	@ReturnParameterConfig(format = Format.Numeric, descr = "isAdmin")
	public static final String isAdmin = "isAdmin";

	@ReturnParameterConfig(descr = "报表列表", format = Format.EntityList, entity = ReportEntity.class)
	@FieldsConfig("*")
	@UseDicConfig(field = "creater", dic = UserDic.class)
	public static final String reportList = "reportList";

	@ReturnParameterConfig(descr = "报表", format = Format.Entity, entity = ReportEntity.class)
	@FieldsConfig("*")
	public static final String reportDetail = "reportDetail";

	@ReturnParameterConfig(descr = "报表执行列表", format = Format.EntityList, entity = ExecuteEntity.class)
	@FieldsConfig(value = "*", jsonFields = "result")
	public static final String executeList = "executeList";

	@ReturnParameterConfig(format = Format.Numeric, descr = "总记录数")
	public static final String totalrecord = "totalrecord";

	@ReturnParameterConfig(descr = "用户列表", format = Format.EntityList, entity = UserEntity.class)
	@FieldsConfig(value = "*", exclude = { "password" })
	public static final String userList = "userList";
}
