package com.example.bookmyshow_november.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import at.favre.lib.crypto.bcrypt.BCryptFormatter;
import com.example.bookmyshow_november.models.User;
import com.example.bookmyshow_november.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signUpUser(String email, String password){
        User user = new User();
        user.setEmail(email);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        user.setEmail(encoder.encode(password));

        User savedSaver = userRepository.save(user);


        return savedSaver;
    }
}
