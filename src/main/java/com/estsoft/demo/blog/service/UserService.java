package com.estsoft.demo.blog.service;

import com.estsoft.demo.blog.domain.User;
import com.estsoft.demo.blog.dto.AddUserRequest;
import com.estsoft.demo.blog.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository,
                       BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public void signup(AddUserRequest request) {
        userRepository.save(
                new User(request.getEmail(), encoder.encode(request.getPassword()))
        );
    }
}
