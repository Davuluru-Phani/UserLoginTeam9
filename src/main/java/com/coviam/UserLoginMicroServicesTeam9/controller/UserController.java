package com.coviam.UserLoginMicroServicesTeam9.controller;

import com.coviam.UserLoginMicroServicesTeam9.dto.UserFrontEnd;
import com.coviam.UserLoginMicroServicesTeam9.dto.UserLogin;
import com.coviam.UserLoginMicroServicesTeam9.entity.User;
import com.coviam.UserLoginMicroServicesTeam9.services.UserServices;
import com.coviam.UserLoginMicroServicesTeam9.services.UserTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@Slf4j
public class UserController {

    @Autowired
    UserServices userServices;

    @Autowired
    UserTokenService userTokenService;

    @RequestMapping(method = RequestMethod.POST, value = "/addProfile") //name
    public ResponseEntity<Boolean> addUser(@RequestBody User userProfile) {
//        UserProfile user = new UserProfile();
//        user.setUserEmail(userProfile.getUserEmail());
//        user.setUserPassword(userProfile.getUserPassword());
//        user.setUserName(userProfile.getUserName());
//        user.setUserAddress(userProfile.getUserAddress());
//        user.setUserImgUrl(userProfile.getUserImgUrl());
        userServices.save(userProfile);
        return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/editUser")  //name
    public ResponseEntity<Boolean> editUser(@RequestBody User userProfile) {
//        User user = new User();
//        BeanUtils.copyProperties(userProfile, user);

        return new ResponseEntity<Boolean>(userServices.save(userProfile), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/checkUuid")  //name
    public ResponseEntity<String> checkUuid(@RequestBody String uuid) {
//        UserTokenDTO userTokenDTO=new UserTokenDTO();
//        BeanUtils.copyProperties();
        return new ResponseEntity<String>(userTokenService.checkUuid(uuid).get().getUsermail(), HttpStatus.CREATED);
    }

//    @RequestMapping("/getUuid")
//    public ResponseEntity<UserToken> getUuid(Email email) {
//        return new ResponseEntity<UserToken>(userTokenService.getUuid(email),HttpStatus.CREATED);
//    }

//    @RequestMapping(method = RequestMethod.POST, value = "/generateUuid")  //uuid
//    public ResponseEntity<String> generateUuid(@RequestBody String email) {
//        UserTokenDTO userTokenDTO=new UserTokenDTO();
//        UserToken userToken=userTokenService.generateUuid(email);
//        userTokenDTO.setUserEmail(email);
//        userTokenDTO.setUuid(userToken.getUuid());
//        BeanUtils.copyProperties(userToken,userTokenDTO);
//        return new ResponseEntity<String>(userTokenDTO.getUuid(),HttpStatus.CREATED);
//    }

    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public ResponseEntity<Boolean> logoutButton(@RequestBody String uuid) {
        return new ResponseEntity<Boolean>(userTokenService.deleteUuid(uuid), HttpStatus.CREATED);
    }

    /*,HttpServletResponse response*/
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<?> loginButton(@RequestBody UserLogin userLogin) {
//        response.addHeader("sid",userTokenService.generateUuid(userLogin.getUserEmail()).getUuid());
        String userEmail = userLogin.getUserEmail();
        String userPassword = userLogin.getUserPassword();
        if (!StringUtils.isEmpty(userEmail) && !StringUtils.isEmpty(userPassword)) {
            User user = userServices.getUserByEmail(userLogin.getUserEmail());
            if (user.getUserPassword().equals(userLogin.getUserPassword())) {
                UserFrontEnd userFrontEnd = new UserFrontEnd();
                BeanUtils.copyProperties(user, userFrontEnd);
                return new ResponseEntity<UserFrontEnd>(userFrontEnd, HttpStatus.CREATED);
            }
        } else {
            log.debug("Username or password is null");
        }
        return new ResponseEntity<Boolean>(false, HttpStatus.CREATED);
    }


    @RequestMapping(method = RequestMethod.GET, value = "/mobilelogin")
    public ResponseEntity<Boolean> loginMobileButton(@RequestBody UserLogin userLogin) {
        return new ResponseEntity<Boolean>(userServices.getPass(userLogin.getUserEmail()).equals(userLogin.getUserPassword()), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addPassword")
    public ResponseEntity<Boolean> addPassword(@RequestBody UserLogin userLogin) {
        return new ResponseEntity<Boolean>(userServices.addPass(userLogin), HttpStatus.CREATED);
    }
}
