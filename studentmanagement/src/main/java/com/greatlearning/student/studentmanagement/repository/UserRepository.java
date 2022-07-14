package com.greatlearning.student.studentmanagement.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.greatlearning.student.studentmanagement.model.User;


public interface UserRepository  extends JpaRepository<User, Integer>{
	User findByUsername(String name);

}
