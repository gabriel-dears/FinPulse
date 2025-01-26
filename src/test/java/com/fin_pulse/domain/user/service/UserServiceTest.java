package com.fin_pulse.domain.user.service;

import com.fin_pulse.domain.user.model.Role;
import com.fin_pulse.domain.user.model.User;
import com.fin_pulse.domain.user.repository.RoleRepository;
import com.fin_pulse.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCreateUser() {
        // Arrange
        when(passwordEncoder.encode(anyString())).thenReturn("ENCODED_PASSWORD");
        Role role = new Role();
        role.setName("USER");
        when(roleRepository.findByName(anyString())).thenReturn(role);
        // Act
        User user = userService.registerUser("USERNAME", "EMAIL", "PASSWORD");
        // Assert
        verify(userRepository, times(1)).save(any(User.class));
        assertEquals("USERNAME", user.getUsername());
        assertEquals("EMAIL", user.getEmail());
        assertEquals("ENCODED_PASSWORD", user.getPasswordHash());
    }

}