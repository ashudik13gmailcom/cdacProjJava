package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Branch;
import com.app.pojos.Preference;
import com.app.pojos.User;


@Repository
@Transactional
public class PreferenceDaoImpl implements IPreferenceDao
{
	@Autowired
	private SessionFactory sf;

	@Override
	public List<Preference> getPrefByUserId(User user)
	{
		String jpql="select p from Preference p where user=:user ";
		return sf.getCurrentSession().createQuery(jpql,Preference.class).setParameter("user",user).getResultList();
	}

	@Override
	public void updatePref(Branch branch)
	{
		sf.getCurrentSession().update(branch);
		
	}

	@Override
	public void updatePrefForAllocation(Preference allocatedClg) {
		sf.getCurrentSession().update(allocatedClg);
		
	}

	
	

	

	
	
}
