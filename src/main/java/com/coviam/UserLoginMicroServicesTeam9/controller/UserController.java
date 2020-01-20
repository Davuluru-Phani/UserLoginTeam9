package com.coviam.UserLoginMicroServicesTeam9.controller;

import com.coviam.UserLoginMicroServicesTeam9.dto.UserDTO;
import com.coviam.UserLoginMicroServicesTeam9.dto.UserLogin;
import com.coviam.UserLoginMicroServicesTeam9.dto.UserTokenDTO;
import com.coviam.UserLoginMicroServicesTeam9.entity.User;
import com.coviam.UserLoginMicroServicesTeam9.entity.UserToken;
import com.coviam.UserLoginMicroServicesTeam9.services.UserServices;
import com.coviam.UserLoginMicroServicesTeam9.services.UserTokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Email;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserServices userServices;

    @Autowired
    UserTokenService userTokenService;

    @RequestMapping(method = RequestMethod.POST, value = "/add") //name
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO){
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        User userCreated=userServices.save(user);

        return new ResponseEntity<String>(userCreated.getUserName(),HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editUser")  //name
    public ResponseEntity<String> editUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        User userCreated=userServices.save(user);

        return new ResponseEntity<String>(userCreated.getUserName(),HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkUuid")  //name
    public ResponseEntity<String> checkUuid(@RequestBody String uuid) {
//        UserTokenDTO userTokenDTO=new UserTokenDTO();
//        BeanUtils.copyProperties();
        return new ResponseEntity<String>(userTokenService.checkUuid(uuid).get().getUsermail(),HttpStatus.CREATED);
    }

//    @RequestMapping("/getUuid")
//    public ResponseEntity<UserToken> getUuid(Email email) {
//        return new ResponseEntity<UserToken>(userTokenService.getUuid(email),HttpStatus.CREATED);
//    }

    @RequestMapping(method = RequestMethod.POST, value = "/generateUuid")  //uuid
    public ResponseEntity<String> generateUuid(@RequestBody String email) {
        UserTokenDTO userTokenDTO=new UserTokenDTO();
        UserToken userToken=userTokenService.generateUuid(email);
        userTokenDTO.setUserEmail(email);
        userTokenDTO.setUuid(userToken.getUuid());
        BeanUtils.copyProperties(userToken,userTokenDTO);
        return new ResponseEntity<String>(userTokenDTO.getUuid(),HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public ResponseEntity<Boolean> logoutButton(@RequestBody String uuid){
        return new ResponseEntity<Boolean>(userTokenService.deleteUuid(uuid),HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public ResponseEntity<Boolean> loginButton(@RequestBody UserLogin userLogin){
        return new ResponseEntity<Boolean>(userServices.getPass(userLogin.getUserEmail()).equals(userLogin.getUserPassword()),HttpStatus.CREATED);
    }
}
