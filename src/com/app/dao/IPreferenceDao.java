package com.app.dao;

import java.util.List;

import com.app.pojos.Branch;
import com.app.pojos.Preference;
import com.app.pojos.User;

public interface IPreferenceDao {

	//Preference getPrefByUserId();

	List<Preference> getPrefByUserId(User user);

	void updatePref(Branch branch);

	void updatePrefForAllocation(Preference allocatedClg);


}
