package com.coviam.UserLoginMicroServicesTeam9.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDTO {

    private String userEmail;
    private String userPassword;
    private String userName;
    private String userAddress;
    private String userImgUrl;
    private String userLastLogin;

}
