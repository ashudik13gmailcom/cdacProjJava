package com.app.controller;

import java.util.ArrayList;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IBranchDao;
import com.app.dao.IuserDao;
import com.app.pojos.Branch;
import com.app.pojos.IdBranch;
import com.app.pojos.Otp;
import com.app.pojos.Preference;
import com.app.pojos.User;
import com.app.pojos.UserRole;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	IuserDao iuserDao;
	
	@Autowired
	IBranchDao branchDao;

	@Autowired
	private JavaMailSender mailSender;

	@PostMapping("/register")
	public Integer register(@RequestBody User user) 
	{

		if (user != null)
		{
			user.setRole(UserRole.STUDENT);

			// hs.setAttribute("OTP", otp);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(user.getEmail());
			mailMessage.setSubject("registration email");
			mailMessage.setText("u r registered successfully");
			try 
			{
				mailSender.send(mailMessage);
			} 
			catch (MailException e) 
			{
				System.out.println("inside mail exception");
				e.printStackTrace();
			}

		}
		return iuserDao.registerUser(user);
		

	}	

	@PostMapping("/forgot")
	public Integer forgotPassword(@RequestBody User user)
	{

		user = iuserDao.findByEmail(user);
		System.out.println(user);
		try
		{		System.out.println(user);
			if(user !=null)
			{
				Otp otp=new Otp();
				otp.setOtp(iuserDao.generateOtp());
				iuserDao.saveOtp(otp);
				String msg="Your one time password for forgot password is = "+otp;
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.getEmail());
				mailMessage.setSubject("One Time Password");
				mailMessage.setText(msg);
				try
				{
					mailSender.send(mailMessage);
				}
				catch (MailException e) 
	            {
					System.out.println("inside mail exception");
					e.printStackTrace();
				}
				return 1;
			}
		} catch (NoResultException e) 
		{
			System.out.println("in the exception");
			e.printStackTrace();
		}
		return 0;
	}@PostMapping("/confirmOtp")
	public boolean confirmOtp(@RequestBody Otp otp)
	{
		Otp o=iuserDao.getOtp();
		System.out.println(otp.getOtp());
		System.out.println(o.getOtp());
		if(otp.getOtp()==o.getOtp())
		{
			iuserDao.deleteOtp();
			return true;
		}
		else
		{
			System.out.println("in false");
			return false;
		}
	}
	@PostMapping("/resetpassword")
	public boolean resetPassword(@RequestBody User user)
	{	System.out.println(user.getPassword());
	System.out.println(user);
		iuserDao.resetPassword(user);
	
		return true;
	}

	
	
	@PostMapping("/login")
	public User login(@RequestBody User user) {
		System.out.println(user);
		return iuserDao.login(user);
	}

	
	
	@PostMapping("/preferencesOfUser")
	public Integer addPreference(@RequestBody IdBranch[] pref)
	{
		
		System.out.println("in adding preferences"+pref);
		
		for (IdBranch preference : pref)
		{
			User u=new User();
			u=iuserDao.getUserById(preference.getUserId());
			//
			
			System.out.println(u);
			
			Branch br=new Branch();
			br=branchDao.findByCollegeNameAndBranchName(preference.getCollegeName(), preference.getBranchName());
			
			Preference newpref=new Preference();
			newpref.setBranch(br);
			newpref.setUser(u);
			newpref.setAlloted(false);
			System.out.println(newpref);
			System.out.println(newpref.isAlloted());
			iuserDao.addPreference(newpref);
			
		}
		return pref.length;
		
		
	}
	
	
	
}
