package com.sean.example.bean;

import com.sean.common.ioc.BeanConfig;
import com.sean.example.entity.ExecuteEntity;
import com.sean.example.entity.ReportEntity;
import com.sean.persist.core.Dao;
import com.sean.persist.core.PageData;
import com.sean.persist.enums.OrderEnum;
import com.sean.persist.ext.Condition;
import com.sean.persist.ext.Order;

@BeanConfig("")
public class ReportBean
{
	public PageData<ReportEntity> getReportList(long creater, int pageNo, int pageSize)
	{
		return Dao.getListByPage(ReportEntity.class, new Condition("creater", creater), new Order("reportId", OrderEnum.Desc), pageNo, pageSize, -1);
	}

	public ReportEntity getReportById(long reportId)
	{
		return Dao.loadById(ReportEntity.class, reportId);
	}

	public void deleteReport(long reportId)
	{
		Dao.remove(ExecuteEntity.class, "reportId", reportId);
		Dao.remove(ReportEntity.class, reportId);
	}
}
