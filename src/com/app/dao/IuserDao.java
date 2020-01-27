package com.app.dao;

import com.app.pojos.Branch;
import com.app.pojos.Otp;
import com.app.pojos.Preference;
import com.app.pojos.User;

public interface IuserDao {
	int registerUser(User user);
	int generateOtp();
	public User login(User user);
	Integer addPreference(Preference pref);
	User getUserByEmail(String email);
	
	User getUserById(int id);
	User findByEmail(User user);
	void saveOtp(Otp otp);
	Otp getOtp();
	void deleteOtp();
	void resetPassword(User user);

}
