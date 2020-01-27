package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IPreferenceDao;
import com.app.dao.IuserDao;
import com.app.pojos.Branch;
import com.app.pojos.CollegeBranch;
import com.app.pojos.Preference;
import com.app.pojos.User;

@Service
public class PreferenceServiceImpl implements PreferenceService {
	
	@Autowired
	IPreferenceDao prefDao;
	
	@Autowired
	IuserDao userDao;

	@Override
	public CollegeBranch getAllocatedBranch(int uid) {
		
		CollegeBranch collegeBranch = new CollegeBranch();
		
		User user = userDao.getUserById(uid);
		
		List<Preference> preferenceList = prefDao.getPrefByUserId(user);
		
		Boolean isAllocated = false;
		
		for(Preference preference:preferenceList) {
			
			isAllocated = false;
			
			List<User> userList = preferenceList
			.parallelStream()
			.filter((pref) -> pref.getBranch().getBranch_id() == preference.getBranch().getBranch_id())
			.map(pref -> pref.getUser()).collect(Collectors.toList());
			
			isAllocated = userList
			.parallelStream()
			.sorted((u1,u2) -> u2.getCet() - u1.getCet())
			.limit(2)
			.anyMatch(u -> u.getUserId() == user.getUserId());
			
			if(isAllocated) {
				prefDao.updatePrefForAllocation(preference);
				collegeBranch.setCollegeName(preference.getBranch().getCollege().getCollegeName());
				collegeBranch.setBranchName(preference.getBranch().getBranchName());
				break;
			}
		}
		
		return collegeBranch;
	}
}
