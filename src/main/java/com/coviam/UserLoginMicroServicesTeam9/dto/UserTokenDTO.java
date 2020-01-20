package com.coviam.UserLoginMicroServicesTeam9.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class UserTokenDTO {
    private String uuid;
    private String userEmail;
}
