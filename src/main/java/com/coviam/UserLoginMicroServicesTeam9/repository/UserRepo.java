package com.coviam.UserLoginMicroServicesTeam9.repository;

import com.coviam.UserLoginMicroServicesTeam9.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User,String> {
    User findByUserEmail(String userEmail);


}
