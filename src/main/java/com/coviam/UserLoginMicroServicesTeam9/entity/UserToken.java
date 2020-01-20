package com.coviam.UserLoginMicroServicesTeam9.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Getter @Setter
@Table(name="usertoken")
public class UserToken {

    @Id
    @Column(name = "id")
    private String uuid;

    private String Usermail;

}