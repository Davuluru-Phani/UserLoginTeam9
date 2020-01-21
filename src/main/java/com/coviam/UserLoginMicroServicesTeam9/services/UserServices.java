package com.coviam.UserLoginMicroServicesTeam9.services;

import com.coviam.UserLoginMicroServicesTeam9.dto.UserLogin;
import com.coviam.UserLoginMicroServicesTeam9.dto.UserProfile;
import com.coviam.UserLoginMicroServicesTeam9.entity.User;

public interface UserServices {
    public boolean save(User userProfile);
    public String getPass(String email);
    public boolean addPass(UserLogin userLogin);
    public User getUserByEmail(String email);
}