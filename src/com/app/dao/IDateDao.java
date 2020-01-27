package com.app.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.app.pojos.DateTable;

public interface IDateDao 
{

	DateTable getDate();

	void changeDate(DateTable dt);

	

}
