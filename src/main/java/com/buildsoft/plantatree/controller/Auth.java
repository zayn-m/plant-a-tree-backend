package com.buildsoft.plantatree.controller;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.buildsoft.plantatree.common.RoleName;
import com.buildsoft.plantatree.common.UserStatus;
import com.buildsoft.plantatree.dto.UserDto;
import com.buildsoft.plantatree.message.request.LoginForm;
import com.buildsoft.plantatree.message.request.SignUpForm;
import com.buildsoft.plantatree.message.response.JwtResponse;
import com.buildsoft.plantatree.model.Role;
import com.buildsoft.plantatree.model.User;
import com.buildsoft.plantatree.repository.RoleRepository;
import com.buildsoft.plantatree.repository.UserRepository;
import com.buildsoft.plantatree.security.jwt.JwtProvider;
import com.buildsoft.plantatree.service.AuthService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class Auth {
	@Autowired
	private AuthService authService;
	
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtProvider jwtProvider;
    
    @Autowired
    private AuthenticationManager authenticationManager;
	
	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if(userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Fail -> Username is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<String>("Fail -> Email is already in use!",
                    HttpStatus.BAD_REQUEST);
        }

        boolean userCreated = authService.createUser(signUpRequest);
        
        if (userCreated == true) {
        	return ResponseEntity.ok().body("User registered successfully!");	
        }
        
        return ResponseEntity.ok().body("User registration failed!");
    }
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        String jwt = authService.loginUser(loginRequest);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
	
	@GetMapping("/authorize")
	public void authorizeUser() {
		authService.authorize();
	}
}
