package com.sean.example.bean;

import java.util.List;

import com.sean.common.ioc.BeanConfig;
import com.sean.example.entity.UserEntity;
import com.sean.persist.core.Dao;
import com.sean.persist.enums.ConditionEnum;
import com.sean.persist.ext.Condition;

@BeanConfig("")
public class UserBean
{
	public List<UserEntity> getUserList()
	{
		return Dao.getListByCond(UserEntity.class, new Condition("userId", ConditionEnum.Not_Equal, 0));
	}
}
