package com.assignment.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import com.assignment.demo.Repository.UserRepo;
import com.assignment.demo.Request.UserRequest;
import com.assignment.demo.Model.UserModel;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public List<UserModel>getUserList(){
        return userRepo.findAll();
    }

    public void createUser(UserRequest userRequest){
        UserModel userModel = new UserModel();
        userModel.setName(userRequest.getName());
        userModel.setEmail(userRequest.getEmail());
        userRepo.save(userModel);
    }

    public void updateUser(Long id, UserRequest userRequest) throws Exception{
        UserModel userModel = userRepo.findById(id).orElseThrow(() -> new Exception("Can not found the user."));
        if(Objects.nonNull(userRequest.getName()) && userModel.getName() != userRequest.getName()){
            userModel.setName(userRequest.getName());
        } else if(Objects.nonNull(userRequest.getEmail()) && userModel.getEmail() != userRequest.getEmail()){
            userModel.setEmail(userRequest.getEmail());
        }
        userRepo.save(userModel);
        
    }

    public void deleteUser(Long id) throws Exception{
        UserModel userModel = userRepo.findById(id).orElseThrow(() -> new Exception("Can not found the user."));

        userRepo.delete(userModel);
        
        
    }
}
