package com.coviam.UserLoginMicroServicesTeam9.controller;


import com.coviam.UserLoginMicroServicesTeam9.dto.EmailDTO;
import com.coviam.UserLoginMicroServicesTeam9.dto.FalseDto;
import com.coviam.UserLoginMicroServicesTeam9.dto.OtpDto;
import com.coviam.UserLoginMicroServicesTeam9.dto.OtpStatusDto;
import com.coviam.UserLoginMicroServicesTeam9.services.OtpService;
import com.coviam.UserLoginMicroServicesTeam9.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/otp")
public class  OtpController {
    @Autowired
    OtpService otpService;

    @Autowired
    UserServices userServices;

    @PostMapping(path = "/get")
    public ResponseEntity<?> getOtp(@RequestHeader Map<String, String> headerss, @RequestBody EmailDTO emailDTO) {

        if(userServices.checkEmail(emailDTO.getUserEmail())) {

            headerss.forEach((key, value) -> {
                System.out.println(String.format("Header '%s' = %s", key, value));
            });
            OtpDto otpDto = new OtpDto();
            try {
                otpDto = otpService.sendEmail(emailDTO.getUserEmail());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            OtpStatusDto otpStatusDto=new OtpStatusDto();
            otpStatusDto.setOtp(otpDto.getOtp());
            otpStatusDto.setStatus(true);
            return new ResponseEntity<OtpStatusDto>(otpStatusDto, HttpStatus.OK);
        }

        else {
            FalseDto falseDto=new FalseDto();
            falseDto.setStatus(false);
            return new ResponseEntity<FalseDto>(falseDto, HttpStatus.OK);
        }


    }
}
