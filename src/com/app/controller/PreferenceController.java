package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IPreferenceDao;
import com.app.dao.IuserDao;
import com.app.pojos.Branch;
import com.app.pojos.College;
import com.app.pojos.CollegeBranch;
import com.app.pojos.Preference;
import com.app.pojos.User;
import com.app.service.PreferenceService;

@CrossOrigin(allowedHeaders="*")
@RestController
@RequestMapping("/checkPreference")
public class PreferenceController 
{
	@Autowired
	IPreferenceDao prefDao;
	
	@Autowired
	IuserDao dao;
	
	@Autowired
	PreferenceService prefService;
	
	User user=new User();
	List<Preference> prefs =new ArrayList<Preference>();
	Preference allocatedClg=new Preference();
	
	public PreferenceController()
	{
		
	}
	
	
	@GetMapping("/{uid}")
	public CollegeBranch isAllocated(@PathVariable int uid)
	{
		/*CollegeBranch cb=new CollegeBranch();
		 cb=prefService.getAllocatedBranch(uid);
		 System.out.println(cb);
		 return cb;
		*/
		
		System.out.println("in Is Allocated");
		user=dao.getUserById(uid);
		System.out.println(user);
		prefs= prefDao.getPrefByUserId(user);
		System.out.println(prefs);
		
		for (Preference preference : prefs) 
		{
			if(preference.isAlloted()==true)
			{
				CollegeBranch cb=new CollegeBranch();
				cb.setBranchName(preference.getBranch().getBranchName());
				cb.setCollegeName(preference.getBranch().getCollege().getCollegeName());
				return cb;
				
			}
			
		}
		
		
		
		boolean flag=false;
		for (Preference preference : prefs)
		{
			if(preference.getBranch().getCriteria()<= user.getCet() && preference.getBranch().getAvailable_seats()>0)
			{
				System.out.println(preference.getBranch().getCriteria());
				flag=true;
				
				
				int var=preference.getBranch().getAllocated_seats()+1;
				preference.getBranch().setAllocated_seats(var);
				
				var=preference.getBranch().getAvailable_seats()-1;
				preference.getBranch().setAvailable_seats(var);
				
				preference.setAlloted(true);
				allocatedClg=preference;
				
				
				prefDao.updatePrefForAllocation(allocatedClg);
				prefDao.updatePref(preference.getBranch());
				
				break;				
				
			}
			
		}
		if(flag)
		{
			CollegeBranch cb=new CollegeBranch();
			cb.setBranchName(allocatedClg.getBranch().getBranchName());
			cb.setCollegeName(allocatedClg.getBranch().getCollege().getCollegeName());
			System.out.println(cb);
			return cb;
		}
		else
			return null;
		
	
	}
	
	
}
