package com.sean.service.core;

import com.sean.service.entity.ParameterEntity;
import com.sean.service.ext.EnumChecker;
import com.sean.service.ext.FileChecker;
import com.sean.service.ext.FloatChecker;
import com.sean.service.ext.IntegerChecker;
import com.sean.service.ext.LongChecker;
import com.sean.service.ext.ParameterChecker;
import com.sean.service.ext.StringChecker;

/**
 * 请求验证
 * @author sean
 */
public final class RequestChecker
{
	public static final ParameterChecker[] checkers = new ParameterChecker[8];

	/**
	 * 初始化验证器
	 */
	public RequestChecker()
	{
		checkers[0] = new IntegerChecker();
		checkers[1] = new LongChecker();
		checkers[2] = new FloatChecker();
		checkers[3] = new StringChecker();
		checkers[4] = new EnumChecker();
		checkers[5] = new FileChecker();
	}

	/**
	 * 验证参数
	 * @param session
	 * @param param
	 * @param nullable
	 * @return
	 */
	public boolean check(Session session, ParameterEntity param, boolean nullable)
	{
		return checkers[param.getDataType().getValue()].check(session, param, nullable);
	}
}
