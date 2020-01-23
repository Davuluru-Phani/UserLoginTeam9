package com.coviam.UserLoginMicroServicesTeam9.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@ToString
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @Column(name = "userEmail")
    private String userEmail;
    private String userPassword;
    private String userName;
    private String userAddress;
    private String userImgUrl;
    private String userLastLogin;

}
