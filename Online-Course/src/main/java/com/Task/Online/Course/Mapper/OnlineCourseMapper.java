package com.Task.Online.Course.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import com.Task.Online.Course.Dto.OnlineCourseDto;
import com.Task.Online.Course.Entities.OnlineCourse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OnlineCourseMapper
{
	private final ModelMapper mapper;
	
	public OnlineCourse toEntity(OnlineCourseDto dto)
	{
		return mapper.map(dto, OnlineCourse.class);
	}
	public OnlineCourseDto toDto(OnlineCourse entity)
	{
		return mapper.map(entity, OnlineCourseDto.class);
	}
}
