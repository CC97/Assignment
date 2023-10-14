package com.assignment.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


import com.assignment.demo.Service.UserService;
import com.assignment.demo.Model.UserModel;
import com.assignment.demo.Request.UserRequest;
import com.assignment.demo.Response.UserResponse;




@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<?> getUserList(){
        List<UserModel> userList = userService.getUserList();
        return ResponseEntity.ok(userList);
    }
    
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest userRequest){
        userService.createUser(userRequest);
        UserResponse userResponse = new UserResponse();
        userResponse.setMessage("User is created successfully.");
        return ResponseEntity.ok(userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
        UserResponse userResponse = new UserResponse();
        try {
            userService.updateUser(id, userRequest);
            userResponse.setMessage("User is updated successfully");
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            userResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(userResponse);
        }
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteUser(@PathVariable Long id){
        UserResponse userResponse = new UserResponse();
        try {
            userService.deleteUser(id);
            userResponse.setMessage("User is deleted successfully");
            return ResponseEntity.ok(userResponse);
        } catch (Exception e) {
            userResponse.setMessage(e.getMessage());
            return ResponseEntity.badRequest().body(userResponse);
        }
    }

    
}
