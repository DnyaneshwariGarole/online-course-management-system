package com.Task.Online.Course.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OnlineCourse
{
	@Id   //Declares primary key
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//Defines how primary key value is generated  (id generated during insert)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	private String duration;
	
	private Double price;
	
	private String description;
	
	private LocalDateTime startDate;
	
	

}
