package com.postgresql.postgresql.Controller;

import com.postgresql.postgresql.Entity.User;
import com.postgresql.postgresql.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Users fetched successfully");
        response.put("status", true);
        response.put("data", users);

        return ResponseEntity.ok(response);
    }

    //This api uses custom DAO query
    @GetMapping("/range")
    public ResponseEntity<?> getAllUsersInRange(
            @RequestParam("min") Long min,
            @RequestParam("max") Long max) {

        List<User> users = userRepository.findUserInIdRange(min, max);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Users fetched successfully");
        response.put("status", true);
        response.put("data", users);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {

        System.out.println(user.getEmail() + " " + user.getAge() + " " + user.getName());
        userRepository.save(user);

        List<User> users = userRepository.findAll();

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User has been created successfully");
        response.put("status", true);
        response.put("data", users);

        return ResponseEntity.ok(response);
    }
}
