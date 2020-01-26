package com.coviam.UserLoginMicroServicesTeam9.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FacebookInputDto {
    private String name;
    private String email;
    private PictureDTO picture;
    private String id;
}
