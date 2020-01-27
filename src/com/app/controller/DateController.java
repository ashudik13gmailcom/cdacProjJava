package com.app.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IDateDao;
import com.app.pojos.DateTable;
import com.fasterxml.jackson.annotation.JsonFormat;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/Date")
public class DateController 
{
	
	@Autowired
	IDateDao dtDao;
	
	@GetMapping("/getDate")
	public Date getDate()
	{
		DateTable dt=new DateTable();
		dt=dtDao.getDate();
		
		
		
		Date tempDate=new Date();
		tempDate=dt.getEventDate();
		return tempDate;
	}
	
	@PostMapping("/changeDate")
	public void changeDate(@RequestBody DateTable dt)
	{
		System.out.println(dt);
		dtDao.changeDate(dt);
	}
	
}
