package com.coviam.UserLoginMicroServicesTeam9.services;

import com.coviam.UserLoginMicroServicesTeam9.entity.UserToken;

import java.util.Optional;
import java.util.UUID;

public interface UserTokenService {
    public UserToken generateUuid(String email);
    public Optional<UserToken> checkUuid(String uuid);
    public boolean deleteUuid(String uuid);
    public String getEmail(String uuid);
}
