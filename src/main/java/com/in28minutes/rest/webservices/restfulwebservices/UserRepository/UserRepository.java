package com.in28minutes.rest.webservices.restfulwebservices.UserRepository;

import com.in28minutes.rest.webservices.restfulwebservices.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
