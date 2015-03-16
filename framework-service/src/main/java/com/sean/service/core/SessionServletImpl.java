package com.sean.service.core;

import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 请求Session Servlet实现
 * @author Sean
 */
public final class SessionServletImpl extends Session
{
	private HttpServletRequest request;
	private ServletResponse response;

	public SessionServletImpl(Action action, ServletRequest req, ServletResponse response)
	{
		super(action);
		this.request = (HttpServletRequest) req;
		this.response = response;

		// 优先从参数种去sid
		this.sid = request.getParameter("sid");
		// 参数种不存在从cookie取
		if (sid == null)
		{
			Cookie[] cookies = request.getCookies();
			if (cookies != null)
			{
				for (Cookie c : cookies)
				{
					if ("sid".equals(c.getName()))
					{
						this.sid = c.getValue();
						break;
					}
				}
			}
		}
	}

	@Override
	public String getParameter(final String name, String defValue)
	{
		String val = request.getParameter(name);
		if (val != null && !val.isEmpty())
		{
			return val;
		}
		return defValue;
	}

	@Override
	public String[] getParameters(final String name, String[] defValue)
	{
		String[] val = request.getParameterValues(name);
		if (val != null && val.length > 0)
		{
			return val;
		}
		return defValue;
	}

	@Override
	public int getIntParameter(String name, int defValue)
	{
		String val = request.getParameter(name);
		if (val != null && !val.isEmpty())
		{
			return Integer.parseInt(val);
		}
		return defValue;
	}

	@Override
	public int[] getIntParameters(String name, int[] defValue)
	{
		String[] tmp = request.getParameterValues(name);
		if (tmp != null && tmp.length > 0)
		{
			int[] list = new int[tmp.length];
			for (int i = 0; i < list.length; i++)
			{
				list[i] = Integer.parseInt(tmp[i]);
			}
			return list;
		}
		return defValue;
	}

	@Override
	public long getLongParameter(String name, long defValue)
	{
		String val = request.getParameter(name);
		if (val != null && !val.isEmpty())
		{
			return Long.parseLong(val);
		}
		return defValue;
	}

	@Override
	public long[] getLongParameters(String name, long[] defValue)
	{
		String[] tmp = request.getParameterValues(name);
		if (tmp != null && tmp.length > 0)
		{
			long[] list = new long[tmp.length];
			for (int i = 0; i < list.length; i++)
			{
				list[i] = Long.parseLong(tmp[i]);
			}
			return list;
		}
		return defValue;
	}

	@Override
	public byte getByteParameter(String name, byte defValue)
	{
		String tmp = request.getParameter(name);
		if (tmp != null && !tmp.isEmpty())
		{
			return Byte.parseByte(tmp);
		}
		return defValue;
	}

	@Override
	public byte[] getByteParameters(String name, byte[] defValue)
	{
		String[] tmp = request.getParameterValues(name);
		if (tmp != null && tmp.length > 0)
		{
			byte[] list = new byte[tmp.length];
			for (int i = 0; i < list.length; i++)
			{
				list[i] = Byte.parseByte(tmp[i]);
			}
			return list;
		}
		return defValue;
	}

	@Override
	public float getFloatParameter(String name, float defValue)
	{
		String tmp = request.getParameter(name);
		if (tmp != null && !tmp.isEmpty())
		{
			return Float.parseFloat(tmp);
		}
		return defValue;
	}

	@Override
	public float[] getFloatParameters(String name, float[] defValue)
	{
		String[] tmp = request.getParameterValues(name);
		if (tmp != null && tmp.length > 0)
		{
			float[] list = new float[tmp.length];
			for (int i = 0; i < list.length; i++)
			{
				list[i] = Float.parseFloat(tmp[i]);
			}
			return list;
		}
		return defValue;
	}

	@Override
	public double getDoubleParameter(String name, double defValue)
	{
		String tmp = request.getParameter(name);
		if (tmp != null && !tmp.isEmpty())
		{
			return Double.parseDouble(tmp);
		}
		return defValue;
	}

	@Override
	public double[] getDoubleParameters(String name, double[] defValue)
	{
		String[] tmp = request.getParameterValues(name);
		if (tmp != null && tmp.length > 0)
		{
			double[] list = new double[tmp.length];
			for (int i = 0; i < list.length; i++)
			{
				list[i] = Double.parseDouble(tmp[i]);
			}
			return list;
		}
		return defValue;
	}

	@Override
	public Map<String, String[]> getParameterMap()
	{
		return request.getParameterMap();
	}

	@Override
	public Part getFile(String name)
	{
		try
		{
			return request.getPart(name);
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getHeader(String header)
	{
		return ((HttpServletRequest) request).getHeader(header);
	}

	@Override
	public void addHeader(String header, String value)
	{
		((HttpServletResponse) response).addHeader(header, value);
	}

	@Override
	public void addDateHeader(String header, long date)
	{
		((HttpServletResponse) response).addDateHeader(header, date);
	}

	@Override
	public String getRootPath()
	{
		return request.getServletContext().getRealPath("/");
	}

	@Override
	public String getRemoteAddress()
	{
		return request.getRemoteAddr();
	}

	@Override
	public int getRemotePort()
	{
		return request.getRemotePort();
	}
}
