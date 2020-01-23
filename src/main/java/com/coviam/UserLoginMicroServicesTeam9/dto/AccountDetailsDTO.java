package com.coviam.UserLoginMicroServicesTeam9.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountDetailsDTO {
    private String userEmail;
    private String userName;
    private String userAddress;
    private String userImgUrl;
}
