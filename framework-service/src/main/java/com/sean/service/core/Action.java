package com.sean.service.core;

import java.util.Random;

import com.sean.service.entity.ActionEntity;
import com.sean.service.entity.ParameterEntity;
import com.sean.service.entity.ReturnParameterEntity;
import com.sean.service.enums.Format;

/**
 * request handle interface
 * @author sean
 */
public abstract class Action
{
	private ActionEntity actionEntity;

	/**
	 * handle method
	 * @param session request session
	 */
	public abstract void execute(Session session) throws Exception;

	/**
	 * initialize action
	 * @param actionEntity action entity
	 */
	public void init(ActionEntity actionEntity)
	{
		if (this.actionEntity == null)
		{
			this.actionEntity = actionEntity;
		}
	}

	/***
	 * parameter checker
	 * @param session
	 * @param checker
	 */
	public boolean checkParams(Session session, RequestChecker checker) throws Exception
	{
		// 必填参数
		for (ParameterEntity it : actionEntity.getMustParams())
		{
			if (!checker.check(session, it, false))
			{
				return false;
			}
		}

		// 可选参数
		for (ParameterEntity it : actionEntity.getOptionalParams())
		{
			if (!checker.check(session, it, true))
			{
				return false;
			}
		}
		return true;
	}

	/**
	 * pseudo implementation
	 * @param session request session
	 */
	protected String pseudo(Session session) throws Exception
	{
		StringBuilder json = new StringBuilder(4096);
		json.append("{");
		ReturnParameterEntity[] returnParams = this.actionEntity.getReturnParams();
		if (returnParams != null && returnParams.length > 0)
		{
			Random random = new Random();
			ReturnParameterEntity param = null;
			for (int i = 0; i < returnParams.length; i++)
			{
				param = returnParams[i];
				if (param.getFormat() == Format.Numeric || param.getFormat() == Format.String)
				{
					json.append("\"").append(param.getName()).append("\":\"").append(random.nextInt(10)).append("\"");
				}
				else if (param.getFormat() == Format.Map || param.getFormat() == Format.Entity || param.getFormat() == Format.AvroEntity)
				{
					json.append("\"").append(param.getName()).append("\":{");
					String[] fields = param.getFields();
					for (int j = 0; j < fields.length; j++)
					{
						json.append("\"").append(fields[j]).append("\":\"").append(random.nextInt(10)).append("\",");
					}
					json.setCharAt(json.length() - 1, '}');
				}
				else if (param.getFormat() == Format.EntityList || param.getFormat() == Format.AvroEntityList || param.getFormat() == Format.Table)
				{
					json.append("\"").append(param.getName()).append("\":[");
					String[] fields = param.getFields();
					for (int j = 0; j < 5; j++)
					{
						json.append('{');
						for (int k = 0; k < fields.length; k++)
						{
							json.append("\"").append(fields[k]).append("\":\"").append(random.nextInt(10)).append("\",");
						}
						json.setCharAt(json.length() - 1, '}');
						json.append(',');
					}
					json.setCharAt(json.length() - 1, ']');
				}
				json.append(',');
			}
		}
		if (json.charAt(json.length() - 1) == ',')
		{
			json.setCharAt(json.length() - 1, '}');
		}
		else
		{
			json.append('}');
		}
		session.success();
		return json.toString();
	}

	/**
	 * get action entity
	 * @return
	 */
	public ActionEntity getActionEntity()
	{
		return this.actionEntity;
	}
}
