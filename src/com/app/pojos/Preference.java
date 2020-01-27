package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Preference {
	private Integer prefeId;
	private User user;
	private Branch branch;
	private boolean isAlloted;
	

	public Preference() {

		System.out.println("In Preference pojo");
	}

	

	public Preference(User user, Branch branch, boolean isAlloted) {
		this.user = user;
		this.branch = branch;
		this.isAlloted = isAlloted;
	}



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPrefeId() {
		return prefeId;
	}

	public void setPrefeId(Integer prefeId) {
		this.prefeId = prefeId;
	}

	

	@ManyToOne
	@JoinColumn(name = "UserId")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@OneToOne
	@JoinColumn(name = "BranchId")
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	

	public boolean isAlloted() {
		return isAlloted;
	}



	public void setAlloted(boolean isAlloted) {
		this.isAlloted = isAlloted;
	}



	@Override
	public String toString() {
		return "Preference [prefeId=" + prefeId + ", user=" + user + ", branch=" + branch + "]";
	}

}
