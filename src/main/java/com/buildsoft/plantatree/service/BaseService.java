package com.buildsoft.plantatree.service;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.buildsoft.plantatree.common.RoleName;
import com.buildsoft.plantatree.model.User;
import com.buildsoft.plantatree.repository.UserRepository;
import com.buildsoft.plantatree.security.services.UserPrinciple;

@Component
public class BaseService {

	private Boolean isRunningTest = null;

	@Autowired
	private PasswordEncoder encoder;
	
	
    @Autowired
    private UserRepository userRepository;
    
    private boolean isRunningTest() {
        if (isRunningTest == null) {
            isRunningTest = true;
            try {
                Class.forName("org.junit.Test");
            } catch (ClassNotFoundException e) {
                isRunningTest = false;
            }
        }
        return isRunningTest;
    }
    
    private boolean findRole(User user, RoleName roleName) {
        AtomicBoolean result = new AtomicBoolean(false);
        user.getRoles().stream().forEach(role -> {
            if (role.getName() == roleName) {
                result.set(true);
            }
        });
        return result.get();
    }
    
    public boolean isAdmin(User user) {
    	return findRole(user, RoleName.ROLE_ADMIN);
    }
    
    public User getLoggedInUser() {
//    	if (this.isRunningTest()) {
//    		return new User("Jon Doe", "jondoe", "jondoe@gmail.com", encoder.encode("12345"));
//    	}

        return userRepository
                .findById(
                        ((UserPrinciple) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId())
                .get();
    }
    
    public void checkAdminAccess() {
//    	if (this.isRunningTest()) { return; }
    	User loggedInUser = getLoggedInUser();

		if (!isAdmin(loggedInUser)) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Not authorized");
		}
    }
}
