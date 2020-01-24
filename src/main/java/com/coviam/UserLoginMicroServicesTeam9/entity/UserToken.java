package com.coviam.UserLoginMicroServicesTeam9.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table
public class UserToken {

    @Id
    private String uuid;
    private String userEmail;

}