package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IBranchDao;
import com.app.dao.ICollegeDao;
import com.app.pojos.Branch;
import com.app.pojos.College;

@CrossOrigin(allowedHeaders = "*")
@RestController
@RequestMapping("/college")
public class CollegeController {

	public CollegeController() 
	{
		System.out.println("in college controller");
		// TODO Auto-generated constructor stub
	}

	@Autowired
	ICollegeDao collDao;
	
	@Autowired
	IBranchDao brDao;

	@GetMapping("/allcollege")
	public List<College> getAllColleges() {
		System.out.println("inside get list controller");

		List<College> clgs = collDao.getAllCollege();

		List<College> clgsRes = new ArrayList<College>();

		for (College college : clgs) {

			College clg = new College();
			clg.setCollegeName(college.getCollegeName());
			clg.setCollegeId(college.getCollegeId());
			clg.setUniversity(college.getUniversity());
			
			List<Branch> branches=new ArrayList<>();
			
			for (Branch branch : college.getBranch())
			{
				Branch br= new Branch();
				br.setBranch_id(branch.getBranch_id());
				br.setBranchName(branch.getBranchName());
				br.setAllocated_seats(branch.getAllocated_seats());
				br.setAvailable_seats(branch.getAvailable_seats());
				br.setTotalSeats(branch.getTotalSeats());
				br.setCriteria(branch.getCriteria());
				branches.add(br);
			}
			clg.setBranch(branches);
			
			clgsRes.add(clg);
		}

		return clgsRes;
	}
	
	@GetMapping("/getclgName/{cid}")
	public College getCollegeById(@PathVariable int cid)
	{
		College clg=new College();
		clg=collDao.getCollegeById(cid);
		return clg;
	}
	
	@DeleteMapping("/deleteclg/{cid}")
	public String deleteClg(@PathVariable int cid)
	{
		
		College clg=new College();
		clg=collDao.getCollegeById(cid);
		collDao.deleteClg(clg);
		return "deleted successfully";
	}
	
	@PostMapping("/addCollege")
	public int addCollege(@RequestBody College clg)
	{
		int add;
		 add=collDao.addCollege(clg);
		 return add;
	}
	
	@PostMapping("/addBranch/{cid}")
	public int addBranch(@RequestBody Branch br,@PathVariable int cid)
	{
		int add;
		College clg=new College();
		clg=collDao.getCollegeById(cid);
		
		br.setCollege(clg);
		add=brDao.addBranch(br);
		return add;
		
	}

}
