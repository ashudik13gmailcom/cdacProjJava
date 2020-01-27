package com.app.pojos;

public class IdBranch {
	private String branchName;
	private int userId;
	private String collegeName;
	
	public IdBranch() {
		// TODO Auto-generated constructor stub
	}
	
	

	public IdBranch(String branchName, int userId, String collegeName) {
		super();
		this.branchName = branchName;
		this.userId = userId;
		this.collegeName = collegeName;
	}



	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}



	@Override
	public String toString() {
		return "IdBranch [branchName=" + branchName + ", userId=" + userId + ", collegeName=" + collegeName + "]";
	}
	
	
	
}
	
	