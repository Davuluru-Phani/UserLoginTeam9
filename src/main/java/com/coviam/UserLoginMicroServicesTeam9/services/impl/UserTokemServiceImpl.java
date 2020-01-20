package com.coviam.UserLoginMicroServicesTeam9.services.impl;
import com.coviam.UserLoginMicroServicesTeam9.entity.UserToken;
import com.coviam.UserLoginMicroServicesTeam9.repository.UserTokenRepo;
import com.coviam.UserLoginMicroServicesTeam9.services.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserTokemServiceImpl implements UserTokenService {
    @Autowired
    UserTokenRepo userTokenRepo;

    public UserToken generateUuid(String email){
        UUID uuid=UUID.randomUUID();
        UserToken userToken=new UserToken();
        userToken.setUuid(uuid.toString());
        userToken.setUsermail(email);
        userTokenRepo.save(userToken);
//        System.out.println(userToken.toString());
        return userToken;
    }

    public Optional<UserToken> checkUuid(String uuid){
        return userTokenRepo.findById(uuid);
    }

    public boolean deleteUuid(String uuid){
        if(userTokenRepo.findById(uuid).isPresent()){
            userTokenRepo.deleteById(uuid);
            return true;
        }
        else {
            return false;
        }
    }

}