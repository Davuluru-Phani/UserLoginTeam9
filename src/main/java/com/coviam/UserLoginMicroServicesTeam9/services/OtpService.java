package com.coviam.UserLoginMicroServicesTeam9.services;


import com.coviam.UserLoginMicroServicesTeam9.dto.OtpDto;
import javax.mail.MessagingException;


public interface OtpService {
    OtpDto sendEmail(String userEmail) throws MessagingException;
}
