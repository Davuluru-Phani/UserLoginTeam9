package com.coviam.UserLoginMicroServicesTeam9.services.impl;

import com.coviam.UserLoginMicroServicesTeam9.dto.AccountDetailsDTO;
import com.coviam.UserLoginMicroServicesTeam9.dto.EmailDTO;
import com.coviam.UserLoginMicroServicesTeam9.dto.LoginDetailsDTO;
import com.coviam.UserLoginMicroServicesTeam9.dto.UserLogin;
import com.coviam.UserLoginMicroServicesTeam9.entity.User;
import com.coviam.UserLoginMicroServicesTeam9.entity.UserToken;
import com.coviam.UserLoginMicroServicesTeam9.repository.UserRepo;
import com.coviam.UserLoginMicroServicesTeam9.repository.UserTokenRepo;
import com.coviam.UserLoginMicroServicesTeam9.services.UserServices;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserTokenRepo userTokenRepo;

    public User save(User user) {
        return userRepo.save(user);
    }

    public String getPass(String email) {
        return userRepo.findById(email).get().getUserPassword();
    }

    @Override
    public LoginDetailsDTO checkLoginDetails(UserLogin userLogin) {
        System.out.println("************>" + userLogin.getUserEmail());
        Optional<User> user1 = userRepo.findById(userLogin.getUserEmail());
        LoginDetailsDTO loginDetailsDTO = new LoginDetailsDTO();
        if (user1.isPresent() && user1.get().getUserPassword().equals(userLogin.getUserPassword())) {
            System.out.println("user1 :" + user1.toString());
            UUID uuid = UUID.randomUUID();
            UserToken userToken = new UserToken();
            userToken.setUuid(uuid.toString());
            userToken.setUserEmail(userLogin.getUserEmail());
            userTokenRepo.save(userToken);
            User user = userRepo.findByUserEmail(userLogin.getUserEmail());
            loginDetailsDTO.setLoginStatus(true);
            loginDetailsDTO.setUserName(user.getUserName());
            loginDetailsDTO.setUuid(uuid.toString());
            loginDetailsDTO.setUserImgUrl(user.getUserImgUrl());
            return loginDetailsDTO;
        }
        loginDetailsDTO.setUserImgUrl("");
        loginDetailsDTO.setUuid("");
        loginDetailsDTO.setUserName("");
        loginDetailsDTO.setLoginStatus(false);
        return loginDetailsDTO;
    }

    @Override
    public AccountDetailsDTO getAccountDetails(EmailDTO emailDTO) {
        final Optional<User> byId = userRepo.findById(emailDTO.getUserEmail());
        AccountDetailsDTO accountDetailsDTO = new AccountDetailsDTO();
        if (byId.isPresent()) {
            BeanUtils.copyProperties(byId.get(), accountDetailsDTO);
        }
        return accountDetailsDTO;
    }
}
