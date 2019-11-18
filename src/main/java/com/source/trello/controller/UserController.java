package com.source.trello.controller;

import com.source.trello.message.request.LoginForm;
import com.source.trello.message.request.SignUpForm;
import com.source.trello.message.response.JwtResponse;
import com.source.trello.message.response.ResponseMessage;
import com.source.trello.model.ConfirmationToken;
import com.source.trello.model.Role;
import com.source.trello.model.RoleName;
import com.source.trello.model.User;
import com.source.trello.repository.ConfirmationTokenRepository;
import com.source.trello.repository.UserRepository;
import com.source.trello.security.jwt.JwtProvider;
import com.source.trello.security.service.EmailSenderService;
import com.source.trello.security.service.UserPrinciple;
import com.source.trello.service.RoleService;
import com.source.trello.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailSenderService emailSenderService;

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> userList = (List<User>) userService.findAll();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(), HttpStatus.OK);

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> user1 = userService.findById(id);
        if (!user1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        user1.get().setUserId(user.getUserId());
        user1.get().setEmail(user.getEmail());
        user1.get().setPassword(user.getPassword());
        user1.get().setBoardSet(user.getBoardSet());
        user1.get().setUsername(user.getUsername());

        userService.save(user1.get());
        return new ResponseEntity<>(user1.get(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            userService.remove(id);
            return new ResponseEntity<>(user.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUserId(),
                userDetails.getEmail(),
                userDetails.getUsername(),
                userDetails.getAuthorities()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpForm signUpRequest) {
        if (userService.existsByUserName(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Username is already taken!Try again!"), HttpStatus.BAD_REQUEST);
        }

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email is already in use!Try again"),
                    HttpStatus.BAD_REQUEST);
        }

        User user1 = new User();

        user1.setUsername(signUpRequest.getUsername());
        user1.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user1.setEmail(signUpRequest.getEmail());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            Role userRole = roleService.findByName(RoleName.USER)
                    .orElseThrow(() -> new RuntimeException("Fail! User Role not find!"));
            roles.add(userRole);
        });

        user1.setRoles(roles);
        userService.save(user1);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.OK);
    }

//    @RequestMapping(value="/register", method = RequestMethod.GET)
//    public ModelAndView displayRegistration(ModelAndView modelAndView, User user)
//    {
//        modelAndView.addObject("user", user);
//        modelAndView.setViewName("register");
//        return modelAndView;
//    }
//
//    @RequestMapping(value="/register", method = RequestMethod.POST)
//    public ModelAndView registerUser(ModelAndView modelAndView, User user)
//    {
//
//        User existingUser = userService.findByEmail(user.getEmail());
//        if(existingUser != null)
//        {
//            modelAndView.addObject("message","This email already exists!");
//            modelAndView.setViewName("error");
//        }
//        else
//        {
//            userService.save(user);
//
//            ConfirmationToken confirmationToken = new ConfirmationToken(user);
//
//            confirmationTokenRepository.save(confirmationToken);
//
//            SimpleMailMessage mailMessage = new SimpleMailMessage();
//            mailMessage.setTo(user.getEmailId());
//            mailMessage.setSubject("Complete Registration!");
//            mailMessage.setFrom("chand312902@gmail.com");
//            mailMessage.setText("To confirm your account, please click here : "
//                    +"http://localhost:8082/confirm-account?token="+confirmationToken.getConfirmationToken());
//
//            emailSenderService.sendEmail(mailMessage);
//
//            modelAndView.addObject("emailId", user.getEmailId());
//
//            modelAndView.setViewName("successfulRegisteration");
//        }
//
//        return modelAndView;
//    }
//
//    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
//    {
//        ConfirmationToken token = confirmationTokenRepository.findAllByConfirmationToken(confirmationToken);
//
//        if(token != null)
//        {
//            User user = userService.findByEmail(token.getUser().getEmailId());
//            user.setEnabled(true);
//            userService.save(user);
//            modelAndView.setViewName("accountVerified");
//        }
//        else
//        {
//            modelAndView.addObject("message","The link is invalid or broken!");
//            modelAndView.setViewName("error");
//        }
//
//        return modelAndView;
//    }
}