package com.Task.Online.Course.Controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Task.Online.Course.Dto.OnlineCourseDto;
import com.Task.Online.Course.Services.OnlineCourseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class OnlineCourseController
{
	private final OnlineCourseService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<OnlineCourseDto> getCourse(@PathVariable Long id)
	{
		return ResponseEntity.ok(service.getCourse(id));
	}
	
	@GetMapping
	public ResponseEntity<List<OnlineCourseDto>> getAllCourses()
	{
		return ResponseEntity.ok(service.getAllCourses());
	}
	
	@GetMapping("/pagination")
	public ResponseEntity<List<OnlineCourseDto>> getCourses(
			@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "3")int size,
			@RequestParam(defaultValue = "name")String sortBy,
			@RequestParam(defaultValue = "asc")String direction)
	{
		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending():
			                       Sort.by(sortBy).ascending();
		
	Pageable pageable = PageRequest.of(page, size, sort);
	
	Page<OnlineCourseDto> result = service.getCourses(pageable);
	
	return ResponseEntity.ok(result.toList());
	}
	
	@PostMapping
	public ResponseEntity<OnlineCourseDto> creataeCourse(@Validated @RequestBody OnlineCourseDto dto)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(service.createCourse(dto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<OnlineCourseDto> updateCourse(@PathVariable Long id, @Validated @RequestBody OnlineCourseDto dto)
	{
		return ResponseEntity.ok(service.updateCourse(id, dto));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable Long id)
	{
		service.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}

	
	@DeleteMapping
	public ResponseEntity<Void> deleteAllCourse()
	{
		service.deleteAllCourse();
		return ResponseEntity.noContent().build();
	}
	
	@PostMapping("/bulk-insert")
	public ResponseEntity<List<OnlineCourseDto>> bulkInsert()
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(service.addBulkCourses());
	}
}
