package com.sean.unittest.core;

import java.util.Map;

import javax.servlet.http.Part;

import com.sean.service.core.Action;
import com.sean.service.core.Session;

/**
 * request session unittest implementation
 * @author sean
 */
public final class SessionTesterImpl extends Session
{
	private Map<String, String[]> parameters;

	public SessionTesterImpl(Action action, Map<String, Object> httpsession, Map<String, String[]> parameters)
	{
		super(action);
		this.parameters = parameters;
	}

	@Override
	public String getParameter(final String name, String defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			return val[0];
		}
		return defValue;
	}

	@Override
	public String[] getParameters(final String name, String[] defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			return val;
		}
		return defValue;
	}

	@Override
	public int getIntParameter(String name, int defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			return Integer.parseInt(val[0]);
		}
		return defValue;
	}

	@Override
	public int[] getIntParameters(String name, int[] defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			int[] list = new int[val.length];
			for (int i = 0; i < list.length; i++)
			{
				list[i] = Integer.parseInt(val[i]);
			}
			return list;
		}
		return defValue;
	}

	@Override
	public long getLongParameter(String name, long defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			return Long.parseLong(val[0]);
		}
		return defValue;
	}

	@Override
	public long[] getLongParameters(String name, long[] defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			long[] list = new long[val.length];
			for (int i = 0; i < list.length; i++)
			{
				list[i] = Long.parseLong(val[i]);
			}
			return list;
		}
		return defValue;
	}

	@Override
	public byte getByteParameter(String name, byte defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			return Byte.parseByte(val[0]);
		}
		return defValue;
	}

	@Override
	public byte[] getByteParameters(String name, byte[] defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			byte[] list = new byte[val.length];
			for (int i = 0; i < list.length; i++)
			{
				list[i] = Byte.parseByte(val[i]);
			}
			return list;
		}
		return defValue;
	}

	@Override
	public float getFloatParameter(String name, float defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			return Float.parseFloat(val[0]);
		}
		return defValue;
	}

	@Override
	public float[] getFloatParameters(String name, float[] defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			float[] list = new float[val.length];
			for (int i = 0; i < list.length; i++)
			{
				list[i] = Float.parseFloat(val[i]);
			}
			return list;
		}
		return defValue;
	}

	@Override
	public double getDoubleParameter(String name, double defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			return Double.parseDouble(val[0]);
		}
		return defValue;
	}

	@Override
	public double[] getDoubleParameters(String name, double[] defValue)
	{
		String[] val = parameters.get(name);
		if (val != null && val.length > 0)
		{
			double[] list = new double[val.length];
			for (int i = 0; i < list.length; i++)
			{
				list[i] = Double.parseDouble(val[i]);
			}
			return list;
		}
		return defValue;
	}

	@Override
	public Map<String, String[]> getParameterMap()
	{
		return parameters;
	}

	@Override
	public String getHeader(String header)
	{
		throw new UnsupportedOperationException("not supported for unit test");
	}

	@Override
	public void addHeader(String header, String value)
	{
		throw new UnsupportedOperationException("not supported for unit test");
	}

	@Override
	public void addDateHeader(String header, long date)
	{
		throw new UnsupportedOperationException("not supported for unit test");
	}

	@Override
	public String getRootPath()
	{
		throw new UnsupportedOperationException("not supported for unit test");
	}

	@Override
	public String getRemoteAddress()
	{
		return "127.0.0.1";
	}

	@Override
	public int getRemotePort()
	{
		return 0;
	}

	@Override
	public Part getFile(String name)
	{
		throw new UnsupportedOperationException("该方法未实现");
	}
}
