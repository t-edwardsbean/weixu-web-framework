package com.sean.example.action;

import com.sean.common.ioc.ResourceConfig;
import com.sean.example.bean.ExecuteBean;
import com.sean.example.bean.ReportBean;
import com.sean.example.constant.M;
import com.sean.example.constant.P;
import com.sean.example.constant.R;
import com.sean.service.annotation.ActionConfig;
import com.sean.service.annotation.DescriptConfig;
import com.sean.service.annotation.MustParamsConfig;
import com.sean.service.annotation.ReturnParamsConfig;
import com.sean.service.core.Action;
import com.sean.service.core.Session;

@ActionConfig(module = M.class, authenticate = false)
@MustParamsConfig({ P.reportId })
@ReturnParamsConfig({ R.reportDetail, R.executeList })
@DescriptConfig("查询报表")
public class InquireReportAction extends Action
{
	@ResourceConfig
	private ReportBean reportBean;
	@ResourceConfig
	private ExecuteBean executeBean;

	@Override
	public void execute(Session session) throws Exception
	{
		long reportId = session.getLongParameter(P.reportId);

		session.setReturnAttribute(R.reportDetail, reportBean.getReportById(reportId));
		session.setReturnAttribute(R.executeList, executeBean.getExecuteList(reportId));
		session.success();
	}
}
