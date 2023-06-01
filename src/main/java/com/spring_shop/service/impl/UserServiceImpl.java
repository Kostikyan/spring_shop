package com.spring_shop.service.impl;

import com.spring_shop.entity.User;
import com.spring_shop.entity.types.UserType;
import com.spring_shop.repository.UserRepository;
import com.spring_shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public void save(User user) {
        Optional<User> byEmail = userRepository.findByEmail(user.getEmail());
        if (byEmail.isEmpty()){
            String password = user.getPassword();
            String encodePassword = passwordEncoder.encode(password);
            user.setType(UserType.USER);
            user.setPassword(encodePassword);
            userRepository.save(user);
        }

    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }
}