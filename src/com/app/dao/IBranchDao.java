package com.app.dao;

import com.app.pojos.Branch;

public interface IBranchDao {

	Branch getBranchById(int id);
	
	Branch findByCollegeNameAndBranchName(String collegeName, String branchName);

	Integer addBranch(Branch br);
}
