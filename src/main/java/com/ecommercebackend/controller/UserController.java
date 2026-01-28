package com.ecommercebackend.controller;

import com.ecommercebackend.model.User;
import com.ecommercebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/verify-admin")
    public ResponseEntity<Map<String, Object>> verifyAdmin(@RequestBody Map<String, String> request) {
        String username = request.get("username");

        Map<String, Object> response = new HashMap<>();

        if (username == null || username.trim().isEmpty()) {
            response.put("isAdmin", false);
            response.put("message", "Username is required");
            return ResponseEntity.badRequest().body(response);
        }

        Optional<User> user = userService.findByUsername(username);

        if (user.isEmpty()) {
            response.put("isAdmin", false);
            response.put("message", "User not found");
            return ResponseEntity.ok(response);
        }

        boolean isAdmin = userService.isAdmin(username);
        response.put("isAdmin", isAdmin);
        response.put("username", username);
        response.put("message", isAdmin ? "Admin verified" : "Not an admin user");

        return ResponseEntity.ok(response);
    }

    // temporary endpoints
//    @PostMapping("/create")
//    public ResponseEntity<User> createUser(@RequestBody Map<String, String> request) {
//        String username = request.get("username");
//        String role = request.get("role");
//
//        User user = userService.createUser(username, role);
//        return ResponseEntity.ok(user);
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userService.deleteUser(id);
//        return ResponseEntity.noContent().build();
//    }

}
