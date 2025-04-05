package com.hb.userservice.Controller;

import com.hb.userservice.Model.User;
import com.hb.userservice.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController
{

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody User user)
    {
        userService.registerUser(user);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());

        if (existingUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
        }

        User foundUser = existingUser.get();
        boolean passwordMatches = passwordEncoder.matches(user.getPassword(), foundUser.getPassword());

        if (!passwordMatches) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials!");
        }

        session.setAttribute("user", foundUser); // Store user in session

        // Return user details (excluding sensitive info like password)
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Login successful!");
        response.put("user", Map.of(
                "id", foundUser.getId(),
                "username", foundUser.getUsername(),
                "email", foundUser.getEmail()
        ));

        return ResponseEntity.ok(response);
    }



    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session)
    {
        if (session.getAttribute("user") == null) {
            return ResponseEntity.ok("alerady logged out");
        }
        session.invalidate();
        return ResponseEntity.ok("User logged out successfully!");
    }


    @GetMapping("/user")
    public ResponseEntity<?> getUser(HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            User updatedUser=userService.findByID(user.getId());
            return ResponseEntity.ok(updatedUser);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No user logged in");
    }


}
