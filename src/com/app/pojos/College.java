package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class College {
	private Integer collegeId;
	private String collegeName;
	private String university;
	
	private List<Branch> branch=new ArrayList<>();
	
	
	public College() {
		System.out.println("in college pojo");
	}

	public College(Integer collegeId, String collegeName, String university) {
		super();
		this.collegeId = collegeId;
		this.collegeName = collegeName;
		this.university = university;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}
	
	@OneToMany(mappedBy="college",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER)
	public List<Branch> getBranch() {
		return branch;
	}

	public void setBranch(List<Branch> branch) {
		this.branch = branch;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	@Override
	public String toString() {
		return "College [collegeId=" + collegeId + ", collegeName=" + collegeName + ", university=" + university + "]";
	}
	
	
	
}
