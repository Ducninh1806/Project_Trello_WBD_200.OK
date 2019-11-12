package com.source.trello.controller;

import com.source.trello.message.request.LoginForm;
//import com.source.trello.message.request.SignUpForm;
import com.source.trello.message.request.SignUpForm;
import com.source.trello.message.response.JwtResponse;
import com.source.trello.message.response.ResponseMessage;
import com.source.trello.model.Board;
import com.source.trello.model.Role;
import com.source.trello.model.RoleName;
import com.source.trello.model.User;
import com.source.trello.repository.RoleRepository;
import com.source.trello.repository.UserRepository;
import com.source.trello.security.jwt.JwtProvider;
import com.source.trello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*",maxAge = 3600)
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping
    public ResponseEntity<List<User>> findAllBoard() {
        List<User> boards = (List<User>) userService.findAll();
        if (boards.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            return new ResponseEntity<>(new ResponseMessage("Username is already taken!Try again!"), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email is already in use!Try again"),
                    HttpStatus.BAD_REQUEST);
        }

        User user1 = new User();

        user1.setUserName(signUpRequest.getUserName());
        user1.setPhoneNumber(signUpRequest.getPhoneNumber());
        user1.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user1.setEmail(signUpRequest.getEmail());

        Set<Role> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            Role userRole = roleRepository.findByName(RoleName.USER)
                    .orElseThrow(() -> new RuntimeException("Fail! User Role not find!"));
            roles.add(userRole);
        });


        user1.setRoles(roles);
        userRepository.save(user1);
        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"),HttpStatus.OK);

    }
}
