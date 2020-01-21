package com.coviam.UserLoginMicroServicesTeam9.services.impl;

import com.coviam.UserLoginMicroServicesTeam9.dto.UserLogin;
import com.coviam.UserLoginMicroServicesTeam9.dto.UserProfile;
import com.coviam.UserLoginMicroServicesTeam9.entity.User;
import com.coviam.UserLoginMicroServicesTeam9.repository.UserRepo;
import com.coviam.UserLoginMicroServicesTeam9.services.UserServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    UserRepo userRepo;

    public boolean save(User user) {
//        User user=new User();
//        BeanUtils.copyProperties(user,userProfile);
        userRepo.save(user);
        return true;
    }

    public String getPass(String email) {
        return userRepo.findById(email).get().getUserPassword();
    }

    public boolean addPass(UserLogin userLogin) {
        User user = new User();
        user.setUserEmail(userLogin.getUserEmail());
        user.setUserPassword(userLogin.getUserPassword());
        userRepo.save(user);
        return true;
    }

    public User getUserByEmail(String email) {
        Optional<User> ur = userRepo.findById(email);
        User userbyid = new User();
        BeanUtils.copyProperties(ur,userbyid);
        return userbyid;
    }

}
