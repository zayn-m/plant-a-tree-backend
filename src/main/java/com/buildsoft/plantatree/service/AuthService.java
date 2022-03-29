package com.buildsoft.plantatree.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.buildsoft.plantatree.common.UserStatus;
import com.buildsoft.plantatree.model.User;
import com.buildsoft.plantatree.repository.UserRepository;

@Service
public class AuthService {
	@Autowired
	private UserRepository userRepository;
	
	public UserStatus createUser(User newUser) {
		System.out.println("New user: " + newUser.toString());
		List<User> users = userRepository.findAll();

        for (User user : users) {
            System.out.println("Registered user: " + newUser.toString());

            if (user.equals(newUser)) {
                System.out.println("User Already exists!");
                return UserStatus.USER_ALREADY_EXISTS;
            }
        }

        userRepository.save(newUser);
        return UserStatus.SUCCESS;
	}
}
