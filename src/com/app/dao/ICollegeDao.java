package com.app.dao;

import java.util.List;

import com.app.pojos.College;

public interface ICollegeDao
{
	List<College> getAllCollege();

	College getCollegeById(int cid);

	void deleteClg(College clg);

	Integer addCollege(College clg);
}
