package com.buildsoft.plantatree.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.buildsoft.plantatree.common.RoleName;
import com.buildsoft.plantatree.common.UserStatus;
import com.buildsoft.plantatree.message.request.LoginForm;
import com.buildsoft.plantatree.message.request.SignUpForm;
import com.buildsoft.plantatree.model.Role;
import com.buildsoft.plantatree.model.User;
import com.buildsoft.plantatree.repository.RoleRepository;
import com.buildsoft.plantatree.repository.UserRepository;
import com.buildsoft.plantatree.security.jwt.JwtProvider;

@Service
public class AuthService extends BaseService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private CartService cartService;
	
	public String loginUser(LoginForm loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        return jwt;
	}
	
	public boolean createUser(SignUpForm signUpRequest) {
	        // Creating user's account
	        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
	                signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));

	        Set<String> strRoles = signUpRequest.getRole();
	        System.out.println(strRoles);
	        Set<Role> roles = new HashSet<>();

	        strRoles.forEach(role -> {
	        	switch(role) {
		    		case "admin":
		    			Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		    			roles.add(adminRole);
		    			
		    			break;
		    		case "pm":
		            	Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		            	roles.add(pmRole);
		            	
		    			break;
		    		default:
		        		Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
		                .orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		        		roles.add(userRole);        			
	        	}
	        });
	        
	        user.setRoles(roles);
	        User newUser = userRepository.save(user);
	        cartService.create(newUser);

	        if (newUser != null) {
	        	return true;
	        }
	        return false;
	}
	
	public void authorize() {
		checkAdminAccess();	
	}
}
