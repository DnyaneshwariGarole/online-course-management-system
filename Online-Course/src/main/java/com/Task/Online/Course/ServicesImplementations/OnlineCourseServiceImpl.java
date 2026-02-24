package com.Task.Online.Course.ServicesImplementations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Task.Online.Course.Dto.OnlineCourseDto;
import com.Task.Online.Course.Entities.OnlineCourse;
import com.Task.Online.Course.Exception.ResourceNotFoundException;
import com.Task.Online.Course.Mapper.OnlineCourseMapper;
import com.Task.Online.Course.Repository.OnlineCourseRepository;
import com.Task.Online.Course.Services.OnlineCourseService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OnlineCourseServiceImpl implements OnlineCourseService
{
	private final OnlineCourseRepository repository;
	
	private final OnlineCourseMapper mapper;
	
	@Override
	public OnlineCourseDto getCourse(Long id)
	{
		OnlineCourse course = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Course Not Found"));
		return mapper.toDto(course);
	}
	
	@Override
	public List<OnlineCourseDto> getAllCourses()
	{
		return repository.findAll().stream().map(mapper::toDto).toList();
	}
	
	@Override
	public OnlineCourseDto createCourse(OnlineCourseDto dto)
	{
		OnlineCourse course = repository.save(mapper.toEntity(dto));
		
		return mapper.toDto(course);
	}
	
	@Override
	public OnlineCourseDto updateCourse(Long id, OnlineCourseDto dto)
	{
	    OnlineCourse existing = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Course Not Found"));

	    existing.setName(dto.getName());
	    existing.setDuration(dto.getDuration());
	    existing.setPrice(dto.getPrice());
	    existing.setDescription(dto.getDescription());
	    existing.setStartDate(dto.getStartDateTime());

	    existing= repository.save(existing);
	    return mapper.toDto(existing);
	}

	
	@Override
	public void deleteCourse(Long id)
	{
		repository.deleteById(id);
	}
	
	
	
	@Override
	public Page<OnlineCourseDto> getCourses(Pageable pageable)
	{
		return repository.findAll(pageable).map(mapper::toDto);
	}
	
	@Override
	public List<OnlineCourseDto> addBulkCourses()
	{
		List<OnlineCourse> list = new ArrayList<>();
		
		list.add(new OnlineCourse(null, "Java FullStack", "6 months", 25000.0, "Java + SpringBoot", null));
		
		list.add(new OnlineCourse(null, "React Development", "4 months", 15000.0, "Frontend Development", null));

	    list.add(new OnlineCourse(null, "Data Science", "6 months", 30000.0,"Python + ML", null));
	      
	    repository.saveAll(list);
	    
	    return list.stream().map(mapper::toDto).toList();
	}

	@Override
	public void deleteAllCourse() 
	{
		repository.deleteAll();
	}

	

}
