package com.coviam.UserLoginMicroServicesTeam9.services;

import com.coviam.UserLoginMicroServicesTeam9.dto.UserLogin;
import com.coviam.UserLoginMicroServicesTeam9.entity.User;
import com.coviam.UserLoginMicroServicesTeam9.entity.UserToken;

public interface UserServices {
    public User save(User user);
    public String getPass(String email);
}