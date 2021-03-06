package com.sean.service.core;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Part;

import org.apache.avro.specific.SpecificRecordBase;

import com.sean.persist.core.Entity;
import com.sean.persist.core.EntityValue;
import com.sean.service.entity.ParameterEntity;
import com.sean.service.enums.ResultState;

/**
 * 请求session
 * @author Sean
 */
public abstract class Session
{
	protected String sid;
	protected long userId = -1;
	protected ResultState state = ResultState.BusinessException;
	protected Map<String, Object> returnData;
	protected String msg = "";
	protected int code = 0;

	// 将所有参数转成实体相应的数据类型
	protected Map<String, Object> entityVals;

	protected Action action;

	public Session(Action action)
	{
		this.action = action;
		this.returnData = new HashMap<String, Object>(8);
	}

	/**
	 * 获取sid
	 * @return
	 */
	public String getSid()
	{
		return sid;
	}

	/**
	 * 读取用户ID
	 * @return
	 */
	public long getUserId()
	{
		return userId;
	}

	/**
	 * 设置登录信息
	 * @param userId userId
	 */
	public void setLoginInfo(long userId)
	{
		if (this.userId == -1)
		{
			this.userId = userId;
		}
	}

	/**
	 * get message which will insert to return json
	 * @return
	 */
	public String getMsg()
	{
		return msg;
	}

	/**
	 * get code
	 * @return
	 */
	public int getCode()
	{
		return this.code;
	}

	/**
	 * add return attribute
	 * @param name
	 * @param value
	 */
	public void setReturnAttribute(String name, int value)
	{
		this.returnData.put(name, value);
	}

	/**
	 * add return attribute
	 * @param name
	 * @param value
	 */
	public void setReturnAttribute(String name, String value)
	{
		this.returnData.put(name, value);
	}

	/**
	 * add return attribute
	 * @param name
	 * @param value
	 */
	public void setReturnAttribute(String name, float value)
	{
		this.returnData.put(name, value);
	}

	/**
	 * add return attribute
	 * @param name
	 * @param value
	 */
	public void setReturnAttribute(String name, long value)
	{
		this.returnData.put(name, value);
	}

	/**
	 * add return attribute
	 * @param name
	 * @param value
	 */
	public void setReturnAttribute(String name, double value)
	{
		this.returnData.put(name, value);
	}

	/**
	 * add return attribute
	 * @param name
	 * @param value
	 */
	public void setReturnAttribute(String name, Map<String, String> value)
	{
		this.returnData.put(name, value);
	}

	/**
	 * add return attribute
	 * @param name
	 * @param value
	 */
	public void setReturnTableAttribute(String name, List<Map<String, String>> value)
	{
		this.returnData.put(name, value);
	}

	/**
	 * add return attribute
	 * @param name
	 * @param value
	 */
	public <E extends Entity> void setReturnAttribute(String name, E value)
	{
		this.returnData.put(name, value);
	}

	/**
	 * add return attribute
	 * @param name
	 * @param value
	 */
	public <E extends Entity> void setReturnAttribute(String name, List<E> value)
	{
		this.returnData.put(name, value);
	}

	/**
	 * add return json attribute
	 * @param name
	 * @param jsonobject
	 */
	public void setReturnJsonAttribute(String name, Object jsonobject)
	{
		this.returnData.put(name, jsonobject);
	}

	/**
	 * add return attribute
	 * @param name
	 * @param value
	 */
	public <E extends SpecificRecordBase> void setReturnAvroAttribute(String name, E value)
	{
		this.returnData.put(name, value);
	}

	/**
	 * add return attribute
	 * @param name
	 * @param value
	 */
	public <E extends SpecificRecordBase> void setReturnAvroAttribute(String name, List<E> value)
	{
		this.returnData.put(name, value);
	}

	/**
	 * clear all return attributes
	 */
	public void clearReturnAttribute()
	{
		this.returnData.clear();
	}

	/**
	 * add return css
	 * @param css
	 */
	public void setReturnCss(String css)
	{
		this.returnData.put("css", css);
	}

	/**
	 * add return javascript
	 * @param js
	 */
	public void setReturnJs(String js)
	{
		this.returnData.put("js", js);
	}

	/**
	 * add return file
	 * @param file
	 */
	public void setReturnFile(File file)
	{
		this.returnData.put("file", file);
	}

	/**
	 * get return attribute map
	 * @return
	 */
	public Map<String, Object> getReturnAttributeMap()
	{
		return this.returnData;
	}

	/**
	 * get request state
	 */
	public ResultState getState()
	{
		return this.state;
	}

	/**
	 * success
	 */
	public void success()
	{
		this.state = ResultState.Success;
	}

	/**
	 * user unlogin
	 */
	public void unlogin()
	{
		this.state = ResultState.Unlogin;
		this.msg = "未登录";
	}

	/**
	 * parameter invalid
	 */
	public void invalid(String msg)
	{
		this.state = ResultState.Invalid;
		this.msg = msg;
	}

	/**
	 * access denied
	 */
	public void denied(String msg)
	{
		this.state = ResultState.Denied;
		this.msg = msg;
	}

	/**
	 * system exception
	 */
	public void exception()
	{
		this.state = ResultState.Exception;
		this.msg = "服务器异常";
	}

	/**
	 * 业务异常
	 * @param msg
	 */
	protected void businessException(String msg, int code)
	{
		this.state = ResultState.BusinessException;
		this.msg = msg;
		this.code = code;
	}

	public abstract String[] getParameters(String name, String[] defValue);

	public abstract String getParameter(String name, String defValue);

	public String getParameter(String name)
	{
		return this.getParameter(name, null);
	}

	public String[] getParameters(String name)
	{
		return this.getParameters(name, null);
	}

	public abstract int getIntParameter(String name, int defValue);

	public abstract int[] getIntParameters(String name, int[] defValue);

	public int getIntParameter(String name)
	{
		return this.getIntParameter(name, 0);
	}

	public int[] getIntParameters(String name)
	{
		return this.getIntParameters(name, null);
	}

	public abstract long getLongParameter(String name, long defValue);

	public abstract long[] getLongParameters(String name, long[] defValue);

	public long getLongParameter(String name)
	{
		return this.getLongParameter(name, 0L);
	}

	public long[] getLongParameters(String name)
	{
		return this.getLongParameters(name, null);
	}

	public abstract byte getByteParameter(String name, byte defValue);

	public abstract byte[] getByteParameters(String name, byte[] defValue);

	public byte getByteParameter(String name)
	{
		return this.getByteParameter(name, (byte) 0);
	}

	public byte[] getByteParameters(String name)
	{
		return this.getByteParameters(name, null);
	}

	public abstract float getFloatParameter(String name, float defValue);

	public abstract float[] getFloatParameters(String name, float[] defValue);

	public float getFloatParameter(String name)
	{
		return this.getFloatParameter(name, 0f);
	}

	public float[] getFloatParameters(String name)
	{
		return this.getFloatParameters(name, null);
	}

	public abstract double getDoubleParameter(String name, double defValue);

	public abstract double[] getDoubleParameters(String name, double[] defValue);

	public double getDoubleParameter(String name)
	{
		return this.getDoubleParameter(name, 0d);
	}

	public double[] getDoubleParameters(String name)
	{
		return this.getDoubleParameters(name, null);
	}

	/**
	 * <p>
	 * get parameter map
	 * </p>
	 */
	public abstract Map<String, String[]> getParameterMap();

	/**
	 * get file
	 * @param name
	 * @return
	 */
	public abstract Part getFile(String name);

	/**
	 * 通过参数注入单个实体
	 * @param entity
	 */
	public void fillSingleEntity(Entity entity)
	{
		if (entity == null)
		{
			throw new NullPointerException("entity is null, can not fill values");
		}
		String tmp = null;

		if (entityVals == null)
		{
			entityVals = new HashMap<>();

			// fill must parameter
			ParameterEntity[] mustParams = action.getActionEntity().getMustParams();
			for (ParameterEntity it : mustParams)
			{
				tmp = this.getParameter(it.getName());
				entityVals.put(it.getName(), RequestChecker.checkers[it.getDataType().getValue()].getValue(tmp));
			}

			// fill optional parameter
			ParameterEntity[] optionalParams = action.getActionEntity().getOptionalParams();
			for (ParameterEntity it : optionalParams)
			{
				tmp = this.getParameter(it.getName());
				if (tmp != null && tmp.length() > 0)
				{
					entityVals.put(it.getName(), RequestChecker.checkers[it.getDataType().getValue()].getValue(tmp));
				}
			}
		}

		// fill into entity
		entity.setValues(new EntityValue(entityVals));
	}

	/**
	 * get remote address
	 * @return
	 */
	public abstract String getRemoteAddress();

	/**
	 * get remote port
	 * @return
	 */
	public abstract int getRemotePort();

	/**
	 * get request header
	 * @param header header name
	 */
	public abstract String getHeader(String header);

	/**
	 * add response header
	 * @param header header name
	 * @param value header value
	 */
	public abstract void addHeader(String header, String value);

	/**
	 * add response date
	 * @param header header name
	 * @param date date(millisecond)
	 */
	public abstract void addDateHeader(String header, long date);

	/**
	 * get application root path
	 */
	public abstract String getRootPath();
}
