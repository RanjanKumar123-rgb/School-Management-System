package com.school.sba.service;

import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.sba.entity.Schedule;
import com.school.sba.repository.ScheduleRepo;

@Service
public class ScheduleService 
{
	@Autowired
	ScheduleRepo scheduleRepo;
	
	public String addSchedule(LocalTime opensAt, LocalTime closesAt, int classHoursPerDay, int classHourLength, int breakTime, int breakLength, int lunchTime, int lunchLength)
	{
		Schedule schedule = new Schedule();
		schedule.setOpensAt(opensAt);
		schedule.setClosesAt(closesAt);
		schedule.setClassHoursPerDay(classHoursPerDay);
		schedule.setClassHourLength(classHourLength);
		schedule.setBreakTime(breakTime);
		schedule.setBreakLength(breakLength);
		schedule.setLunchTime(lunchTime);
		schedule.setLunchLength(lunchLength);
		scheduleRepo.save(schedule);
		
		return "Saved to Database";
	}
	
	public List<Schedule> getAllSchedules()
	{
		return scheduleRepo.findAll();
	}
	
	public Schedule getScheduleById(int scheduleId)
	{
		return scheduleRepo.findById(scheduleId).get();
	}
	
	public String deleteScheduleById(int scheduleId)
	{
		scheduleRepo.deleteById(scheduleId);
		return scheduleId+" deleted";
	}
	
	public String deleteAll() 
	{
		scheduleRepo.deleteAll();
		return "All Schedule objects deleted";
	}
	
	public String updateScheduleById(int scheduleId,   LocalTime opensAt, LocalTime closesAt, int classHoursPerDay, int classHourLength, int breakTime, int breakLength, int lunchTime, int lunchLength)
	{
		Schedule schedule = getScheduleById(scheduleId);
		schedule.setOpensAt(opensAt);
		schedule.setClosesAt(closesAt);
		schedule.setClassHoursPerDay(classHoursPerDay);
		schedule.setClassHourLength(classHourLength);
		schedule.setBreakTime(breakTime);
		schedule.setBreakLength(breakLength);
		schedule.setLunchTime(lunchTime);
		schedule.setLunchLength(lunchLength);
		scheduleRepo.save(schedule);
		
		return scheduleId+" updated";
	}
	
}
