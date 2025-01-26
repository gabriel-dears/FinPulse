package com.fin_pulse.domain.user.service;

import com.fin_pulse.domain.user.model.Role;
import com.fin_pulse.domain.user.model.User;
import com.fin_pulse.domain.user.repository.RoleRepository;
import com.fin_pulse.domain.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder passwordEncoder;

//    TODO: implement user status in the UserService from the application layer
//    private final UserStatusRepository userStatusRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String username, String email, String password) {
        String encryptedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPasswordHash(encryptedPassword);
        Role role = roleRepository.findByName("USER");
        user.setRoles(Set.of(role));
        userRepository.save(user);
        return user;
    }

}
