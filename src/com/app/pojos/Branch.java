package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
public class Branch {
	private Integer branch_id;
	private College college;
	private String branchName;
	private int totalSeats;
	private int allocated_seats;
	private int available_seats;
	private int criteria;

	public Branch() {
		System.out.println("In Branch pojo");
	}

	
	
	public Branch(String branchName, int totalSeats, int allocated_seats, int available_seats, int criteria) {
		super();
		this.branchName = branchName;
		this.totalSeats = totalSeats;
		this.allocated_seats = allocated_seats;
		this.available_seats = available_seats;
		this.criteria = criteria;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(Integer branch_id) {
		this.branch_id = branch_id;
	}

	@ManyToOne
	@JoinColumn(name="collegeId")
	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getAllocated_seats() {
		return allocated_seats;
	}

	public void setAllocated_seats(int allocated_seats) {
		this.allocated_seats = allocated_seats;
	}

	public int getAvailable_seats() {
		return available_seats;
	}

	public void setAvailable_seats(int available_seats) {
		this.available_seats = available_seats;
	}

	public int getCriteria() {
		return criteria;
	}

	public void setCriteria(int criteria) {
		this.criteria = criteria;
	}



	@Override
	public String toString() {
		return "Branch [branch_id=" + branch_id + ", college=" + college + ", branchName=" + branchName
				+ ", totalSeats=" + totalSeats + ", allocated_seats=" + allocated_seats + ", available_seats="
				+ available_seats + ", criteria=" + criteria + "]";
	}

	

}
