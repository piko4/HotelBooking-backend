package com.hb.userservice.Service;

import com.hb.userservice.Model.User;
import com.hb.userservice.Repository.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //-----------------------------
    public User registerUser(User user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        return userRepository.save(user);
    }

    //------------------------------
    public Optional<User> findByEmail(@NotBlank String email)
    {
        return userRepository.findByEmail(email);
    }
    //------------------------------
    public User findByID(UUID id)
    {
        return userRepository.findById(id).orElse(null);
    }
    //------------------------------
}
