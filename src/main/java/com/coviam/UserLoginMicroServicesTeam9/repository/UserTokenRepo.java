package com.coviam.UserLoginMicroServicesTeam9.repository;

import com.coviam.UserLoginMicroServicesTeam9.entity.User;
import com.coviam.UserLoginMicroServicesTeam9.entity.UserToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserTokenRepo extends CrudRepository<UserToken,String> {
}
