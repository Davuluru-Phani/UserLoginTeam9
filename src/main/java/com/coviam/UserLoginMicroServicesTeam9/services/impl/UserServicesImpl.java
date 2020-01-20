package com.coviam.UserLoginMicroServicesTeam9.services.impl;

import com.coviam.UserLoginMicroServicesTeam9.dto.UserLogin;
import com.coviam.UserLoginMicroServicesTeam9.entity.User;
import com.coviam.UserLoginMicroServicesTeam9.repository.UserRepo;
import com.coviam.UserLoginMicroServicesTeam9.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    UserRepo userRepo;

    public User save(User user){
        return userRepo.save(user);
    }

    public String getPass(String email){
        return userRepo.findById(email).get().getUserPassword();
    }
}
