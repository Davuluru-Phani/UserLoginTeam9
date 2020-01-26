package com.coviam.UserLoginMicroServicesTeam9.services;

import com.coviam.UserLoginMicroServicesTeam9.dto.AccountDetailsDTO;
import com.coviam.UserLoginMicroServicesTeam9.dto.EmailDTO;
import com.coviam.UserLoginMicroServicesTeam9.dto.LoginDetailsDTO;
import com.coviam.UserLoginMicroServicesTeam9.dto.UserLogin;
import com.coviam.UserLoginMicroServicesTeam9.entity.User;
import com.coviam.UserLoginMicroServicesTeam9.entity.UserToken;

public interface UserServices {
    public User save(User user);
    public String getPass(String email);

    LoginDetailsDTO checkLoginDetails(UserLogin userLogin);

    AccountDetailsDTO getAccountDetails(EmailDTO emailDTO);

    public Boolean checkEmail(String email);

    public User getUser(String email);
}