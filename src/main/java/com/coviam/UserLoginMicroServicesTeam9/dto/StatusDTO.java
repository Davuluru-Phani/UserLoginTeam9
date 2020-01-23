package com.coviam.UserLoginMicroServicesTeam9.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatusDTO {
    private boolean status;

    public StatusDTO(boolean status) {
        this.status = status;
    }
}
