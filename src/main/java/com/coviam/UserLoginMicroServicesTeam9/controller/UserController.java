package com.coviam.UserLoginMicroServicesTeam9.controller;

import com.coviam.UserLoginMicroServicesTeam9.dto.*;
import com.coviam.UserLoginMicroServicesTeam9.entity.User;
import com.coviam.UserLoginMicroServicesTeam9.entity.UserToken;
import com.coviam.UserLoginMicroServicesTeam9.services.UserServices;
import com.coviam.UserLoginMicroServicesTeam9.services.UserTokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.UUID;


@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @Autowired
    UserTokenService userTokenService;

    @RequestMapping(method = RequestMethod.POST, value = "/add") //name
    public ResponseEntity<StatusDTO> addUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        User userCreated = userServices.save(user);
        if (userCreated != null) {
            return new ResponseEntity<StatusDTO>(new StatusDTO(true), HttpStatus.OK);
        }
        return new ResponseEntity<StatusDTO>(new StatusDTO(false), HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editUser")  //name
    public ResponseEntity<String> editUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO);
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        User userCreated = userServices.save(user);

        return new ResponseEntity<String>(userCreated.getUserName(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkUuid")  //name
    public ResponseEntity<String> checkUuid(@RequestBody String uuid) {
        return new ResponseEntity<String>(userTokenService.checkUuid(uuid).get().getUserEmail(), HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/generateUuid")  //uuid
    public ResponseEntity<String> generateUuid(@RequestBody String email) {
        UserTokenDTO userTokenDTO = new UserTokenDTO();
        UserToken userToken = userTokenService.generateUuid(email);
        userTokenDTO.setUserEmail(email);
        userTokenDTO.setUuid(userToken.getUuid());
        BeanUtils.copyProperties(userToken, userTokenDTO);
        return new ResponseEntity<String>(userTokenDTO.getUuid(), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public ResponseEntity<Boolean> logoutButton(@RequestBody String uuid) {
        return new ResponseEntity<Boolean>(userTokenService.deleteUuid(uuid), HttpStatus.CREATED);
    }

    @GetMapping(path = "/account/display")
    public ResponseEntity<AccountDetailsDTO> getAccountDetails(@Valid @RequestBody EmailDTO emailDTO) {
        return new ResponseEntity<AccountDetailsDTO>(userServices.getAccountDetails(emailDTO), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<LoginDetailsDTO> loginButton(@RequestBody UserLogin userLogin) {
        return new ResponseEntity<LoginDetailsDTO>(userServices.checkLoginDetails(userLogin), HttpStatus.OK);
    }
}
