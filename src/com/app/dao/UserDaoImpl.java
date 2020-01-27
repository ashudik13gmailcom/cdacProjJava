package com.app.dao;

import java.util.Random;

//import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Otp;
//import com.app.pojos.Branch;
import com.app.pojos.Preference;
import com.app.pojos.User;
@Service
@Transactional
public class UserDaoImpl implements IuserDao {

	@Autowired
	SessionFactory sf;
	
	@Override
	public int registerUser(User user) 
	{
		return (Integer) sf.getCurrentSession().save(user) ;
	}
	
	@Override
	public User findByEmail(User user) {
		String jpql="select u from User u where u.email=:em";
		return sf.getCurrentSession().createQuery(jpql,User.class).setParameter("em",user.getEmail()).getSingleResult();
	}

	@Override
	public int generateOtp() 
		{
			Random random = new Random();
			int num = random.nextInt(99999) + 99999;
			if (num < 100000 || num > 999999) 
			{
				num = random.nextInt(99999) + 99999;
				if (num < 100000 || num > 999999)
				{
					System.out.println("Unable to generate PIN at this time..");
				}
			}
			return num;
		}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		System.out.println("Inside Login");
		System.out.println(user);
		String jpql = "select u from User u where u.email = :em and u.password=:pass";
		return sf.getCurrentSession().createQuery(jpql, User.class).setParameter("em", user.getEmail())
				.setParameter("pass", user.getPassword()).getSingleResult();
		
		
	}

	@Override
	public Integer addPreference(Preference pref)
	{
		System.out.println("Inside addPreferences");
		pref.getUser().addPref(pref);	
		//System.out.println(pref);
		return (Integer)sf.getCurrentSession().save(pref) ;
 
	}

	@Override
	public User getUserByEmail(String email) {
		String jpql = "select u from User u where u.email = :em";
		return  sf.getCurrentSession().createQuery(jpql, User.class).setParameter("em",email).getSingleResult();
	}

	@Override
	public User getUserById(int id) {
		User u=new User();
		u=sf.getCurrentSession().get(User.class,id);
		return u;
	}

	@Override
	public void saveOtp(Otp otp) {
		sf.getCurrentSession().save(otp);
		
	}

	@Override
	public Otp getOtp() {
	
		return sf.getCurrentSession().createQuery("select o from Otp o",Otp.class).getSingleResult();
	}

	@Override
	public void deleteOtp() {
		Otp o=sf.getCurrentSession().createQuery("select o from Otp o",Otp.class).getSingleResult();
		sf.getCurrentSession().delete(o);
	}

	@Override
	public void resetPassword(User user) {
		System.out.println(user.getEmail());
		String str = "select u from User u where u.email=:em";
		User u = sf.getCurrentSession().createQuery(str, User.class).setParameter("em", user.getEmail())
				.getSingleResult();
		System.out.println(u);
		u.setPassword(user.getPassword());
		// otp.setOtp(0);
		sf.getCurrentSession().save(u);
		
	}
	
	

}
