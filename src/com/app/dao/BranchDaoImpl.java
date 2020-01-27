package com.app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Branch;

@Service
@Transactional
public class BranchDaoImpl implements IBranchDao {

	@Autowired
	private SessionFactory sf;
	
	
	@Override
	public Branch getBranchById(int id) {
		
		return sf.getCurrentSession().get(Branch.class,id);
		
		/*String jpql="select b from Branch b where branch_id=:bid";
		return sf.getCurrentSession().createQuery(jpql,Branch.class).setParameter("bid",id).getSingleResult();*/
	}


	@Override
	public Integer addBranch(Branch br) {
		//save ->serilizable id return krto mhanun return type Integer
		return (Integer) sf.getCurrentSession().save(br);
	}


	@Override
	public Branch findByCollegeNameAndBranchName(String collegeName, String branchName) {
		String jpql="select b from Branch b join fetch b.college where b.branchName=:branchname and b.college.collegeName=:collegename";
		return sf.getCurrentSession().createQuery(jpql,Branch.class)
				.setParameter("branchname", branchName)
				.setParameter("collegename", collegeName).getSingleResult();
	}

}
