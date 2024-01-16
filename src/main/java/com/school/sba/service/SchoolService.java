package com.school.sba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.sba.entity.Schedule;
import com.school.sba.entity.School;
import com.school.sba.repository.SchoolRepo;

@Service
public class SchoolService 
{
	@Autowired
	SchoolRepo schoolRepo;
	
	@Autowired
	ScheduleService scheduleService;
	
	public String addSchool(int schoolId, String schoolName, String schoolMailId, String schoolAddress)
	{
		School school = new School();
		school.setSchoolId(schoolId);
		school.setSchoolName(schoolName);
		school.setSchoolMailId(schoolMailId);
		school.setSchoolAddress(schoolAddress);
		
		schoolRepo.save(school);
		
		return "Saved to Database";
	}
	
	public List<School> getAllSchool()
	{
		return schoolRepo.findAll();
	}
	
	public School getSchoolById(int schoolId)
	{
		return schoolRepo.findById(schoolId).get();
	}
	
	public String deleteSchoolById(int schoolId)
	{
		schoolRepo.deleteById(schoolId);
		return schoolId+" deleted";
	}
	
	public String deleteAll() 
	{
		schoolRepo.deleteAll();
		return "All school object deleted";
	}
	
	public String updateSchoolById(int schoolId, String schoolName, String schoolMailId, String schoolAddress)
	{
		School school = getSchoolById(schoolId);
		school.setSchoolName(schoolName);
		school.setSchoolMailId(schoolMailId);
		school.setSchoolAddress(schoolAddress);
		schoolRepo.save(school);
		return schoolId+" updated";
	}
	
	public String addScheduleToSchoolById(int scheduleId, int schoolId)
	{
		Schedule schedule = scheduleService.getScheduleById(scheduleId);
		School school = getSchoolById(schoolId);
		school.setSchedule(schedule);
		return schedule+" added to "+schoolId;
	}
}
