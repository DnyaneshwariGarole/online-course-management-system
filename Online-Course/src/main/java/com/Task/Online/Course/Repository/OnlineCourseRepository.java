package com.Task.Online.Course.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Task.Online.Course.Entities.OnlineCourse;

@Repository
public interface OnlineCourseRepository extends JpaRepository<OnlineCourse, Long>
{
	Optional<OnlineCourse>findByName(String name);

}
