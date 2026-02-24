package com.Task.Online.Course.Dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlineCourseDto 
{
	private Long id;
	
	@NotBlank(message = "course name is required")//validation
	private String name;
	
	@NotNull(message = "Duration is required")//it will accept the empty space
	private String duration;
	
	@NotNull(message = "Price is required")
	@Min(value = 0, message = "Price cannot be negative")
	private Double price;
	
	private String description;
	
	private LocalDateTime startDateTime;

}
