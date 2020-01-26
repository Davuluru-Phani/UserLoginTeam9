package com.coviam.UserLoginMicroServicesTeam9.dto;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OtpStatusDto {
    private boolean status;
    private String otp;
}
