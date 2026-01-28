package com.ecommercebackend.service;

import com.ecommercebackend.model.User;
import com.ecommercebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean isAdmin(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return user.isPresent() && "admin".equals(user.get().getRole());
    }

//    public User createUser(String username, String role) {
//        User user = new User(username, role);
//        return userRepository.save(user);
//    }
//
//    public void deleteUser(Long id) {
//        userRepository.deleteById(id);
//    }
}
