package com.sean.example.bean;

import java.util.ArrayList;
import java.util.List;

import com.sean.common.ioc.BeanConfig;
import com.sean.common.ioc.ResourceConfig;
import com.sean.example.entity.ExecuteEntity;
import com.sean.example.entity.ReportEntity;
import com.sean.persist.core.Dao;
import com.sean.persist.enums.OrderEnum;
import com.sean.persist.ext.Condition;
import com.sean.persist.ext.Order;

@BeanConfig("")
public class ExecuteBean
{
	@ResourceConfig
	private ReportBean reportBean;

	public List<ExecuteEntity> getExecuteList(long reportId)
	{
		ReportEntity report = reportBean.getReportById(reportId);
		if (report != null)
		{
			List<Condition> conds = new ArrayList<>(3);
			conds.add(new Condition("reportId", reportId));
			return Dao.getListByCond(ExecuteEntity.class, conds, new Order("executeTime", OrderEnum.Asc));
		}
		return null;
	}

	public void deleteExecute(long executeId)
	{
		Dao.remove(ExecuteEntity.class, executeId);
	}
}
