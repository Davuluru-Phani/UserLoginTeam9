package com.coviam.UserLoginMicroServicesTeam9.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDetailsDTO {
    String uuid;
    private String userName;
    private String userImgUrl;
    private boolean loginStatus;

}
