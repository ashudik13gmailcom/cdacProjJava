package com.app.pojos;

public class CollegeBranch 
{

	private String collegeName;
	private String BranchName;
	
	public CollegeBranch() {
		// TODO Auto-generated constructor stub
	}

	public CollegeBranch(String collegeName, String branchName) {
		super();
		this.collegeName = collegeName;
		BranchName = branchName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getBranchName() {
		return BranchName;
	}

	public void setBranchName(String branchName) {
		BranchName = branchName;
	}

	@Override
	public String toString() {
		return "CollegeBranch [collegeName=" + collegeName + ", BranchName=" + BranchName + "]";
	}
	
	
}
