package com.hb.userservice.Controller;

import com.hb.userservice.Model.User;
import com.hb.userservice.Service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.net.http.HttpResponse;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000/", allowCredentials = "true")
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
    public String login(@RequestBody User user, HttpSession session)
    {

        Optional<User> existingUser = userService.findByUsername(user.getUsername());

        if (existingUser.isPresent())
        {
            String storedPasswordHash = existingUser.get().getPassword();
            System.out.println("User found. Stored Hashed Password: " + storedPasswordHash);

            boolean passwordMatches = passwordEncoder.matches(user.getPassword(), storedPasswordHash);
            System.out.println("Password Match: " + passwordMatches);

            if (passwordMatches)
            {
                session.setAttribute("user", existingUser.get()); // Store user in session
                return "Login successful!";
            }
        } else
        {
            System.out.println("User not found in DB.");
        }

        return "Invalid credentials!";
    }


    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session)
    {
        session.invalidate();
        if (session.getAttribute("user") == null)
        {
            return ResponseEntity.ok("alerady logged out");
        }
        return ResponseEntity.ok("User logged out successfully!");
    }


    @GetMapping("/user")
    public ResponseEntity<?> getSessionUser(HttpSession session)
    {
        User user = (User) session.getAttribute("user");
        if (user == null)
        {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in!");
        }

        return ResponseEntity.ok(user);
    }

}
