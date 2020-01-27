package com.app.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User 
{
	private Integer userId;
	private String name;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dob;
	private String gender;
	private String email;
	private String password;
	private String phone;
	private String address;
	private float ssc;
	private float hsc;
	private int cet;
	private UserRole role;
	@JsonIgnore
	private List<Preference> preference = new ArrayList<>();
	
	
	
	public User()
	{
		System.out.println("In default ctor");
	}
	
	
	
	
	public User(String name, Date dob, String gender, String email, String password, String phone, String address,
			float ssc, float hsc, int cet,UserRole role) {
		super();
		this.name = name;
		this.dob = dob;
		this.gender = gender;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.address = address;
		this.ssc = ssc;
		this.hsc = hsc;
		this.cet = cet;
		this.role=role;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(value = TemporalType.DATE)
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public float getSsc() {
		return ssc;
	}

	public void setSsc(float ssc) {
		this.ssc = ssc;
	}

	public float getHsc() {
		return hsc;
	}

	public void setHsc(float hsc) {
		this.hsc = hsc;
	}

	public int getCet() {
		return cet;
	}

	public void setCet(int cet) {
		this.cet = cet;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@Enumerated(EnumType.STRING)
	public UserRole getRole() {
		return role;
	}


	public void setRole(UserRole role) {
		this.role = role;
	}

	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL,orphanRemoval=true,fetch=FetchType.EAGER)
	public List<Preference> getPreference() {
		return preference;
	}

	public void setPreference(List<Preference> preference) {
		this.preference = preference;
	}
	
	
	//adding preferences to user
	public void addPref(Preference pref)
	{
		this.preference.add(pref);
	}




	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + "]";
	}
	
		



	
	
}
