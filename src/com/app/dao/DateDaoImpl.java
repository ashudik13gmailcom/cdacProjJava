package com.app.dao;

import java.util.Date;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.DateTable;

@Repository
@Transactional
public class DateDaoImpl implements IDateDao {

	@Autowired
	private SessionFactory sf;

	@Override
	public DateTable getDate() {
		
		String jpql="select d from DateTable d";
		return sf.getCurrentSession().createQuery(jpql,DateTable.class).getSingleResult();
	}

	@Override
	public void changeDate(DateTable dt) 
	{
		sf.getCurrentSession().update(dt);
		
	}
	

	
}
