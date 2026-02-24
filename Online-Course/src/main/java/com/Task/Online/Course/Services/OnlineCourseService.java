package com.Task.Online.Course.Services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.Task.Online.Course.Dto.OnlineCourseDto;

public interface OnlineCourseService
{
	OnlineCourseDto getCourse(Long id);
	
	List<OnlineCourseDto> getAllCourses();
	
	OnlineCourseDto createCourse(OnlineCourseDto dto);
	
	OnlineCourseDto updateCourse(Long id, OnlineCourseDto dto);
	
	void deleteCourse(Long id);
	
	void deleteAllCourse();
	
	Page<OnlineCourseDto> getCourses(Pageable pageable);

	List<OnlineCourseDto> addBulkCourses();
}
