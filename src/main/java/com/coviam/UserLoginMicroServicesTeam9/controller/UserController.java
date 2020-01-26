package com.coviam.UserLoginMicroServicesTeam9.controller;

import com.coviam.UserLoginMicroServicesTeam9.dto.*;
import com.coviam.UserLoginMicroServicesTeam9.entity.User;
import com.coviam.UserLoginMicroServicesTeam9.entity.UserToken;
import com.coviam.UserLoginMicroServicesTeam9.services.UserServices;
import com.coviam.UserLoginMicroServicesTeam9.services.UserTokenService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@CrossOrigin(origins = "*")
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

    @GetMapping(path = "/getCookies")
    public ResponseEntity<?> getCookies(@RequestHeader Map<String, String> headerss, HttpServletResponse response, HttpServletRequest request) {

        System.out.println("========");

        /*    final double random = Math.random();
        Cookie cookie = new Cookie("myname", String.valueOf(random));
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
        cookie.setPath("/");

        Cookie[] cookies = request.getCookies();
//        System.out.println("length :" + cookies.length);
        if (cookies != null) {
            Arrays.stream(cookies)
                    .forEach(c -> System.out.println(c.getName() + "=" + c.getValue()));
        }

        response.addCookie(cookie);
        */

        headerss.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });


        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies)
                    .forEach(c -> System.out.println(c.getName() + "=" + c.getValue()));
        }
        Map<String, String> map = new HashMap<>();
        map.put("aa", "bb");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginButton(@RequestHeader Map<String, String> headerss, @RequestBody UserLogin userLogin, HttpServletResponse response, HttpServletRequest request) {
        System.out.println("********");
        headerss.forEach((key, value) -> {
            System.out.println(String.format("Header '%s' = %s", key, value));
        });


        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            Arrays.stream(cookies)
                    .forEach(c -> System.out.println(c.getName() + "=" + c.getValue()));
        }
        LoginDetailsDTO loginDetailsDTO = userServices.checkLoginDetails(userLogin);
        Cookie cookie = new Cookie("uuid", String.valueOf(loginDetailsDTO.getUuid()));
        cookie.setMaxAge(7 * 24 * 60 * 60); // expires in 7 days
//        cookie.setSecure(true);
//        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        HttpHeaders headers = new HttpHeaders();

        headers.add("Set-Cookie", "uuid=" + String.valueOf(loginDetailsDTO.getUuid()));

        System.out.println("return uuid : " + String.valueOf(loginDetailsDTO.getUuid()));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(loginDetailsDTO);

//        return new ResponseEntity<>(loginDetailsDTO, HttpStatus.OK);
        //uuid=2c8984fb-da59-48a3-9308-326873a6cefe
        //uuid=d86d5b3f-8341-43fb-8d40-1fef9e20190b
    }

    //Phani

    @PostMapping("/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody UserLogin userLogin){

        User user=userServices.getUser(userLogin.getUserEmail());
        User newUser=new User();
        BeanUtils.copyProperties(user,newUser);
        newUser.setUserPassword(userLogin.getUserPassword());
        userServices.save(newUser);
        FalseDto falseDto=new FalseDto();
        falseDto.setStatus(false);
        return new ResponseEntity<FalseDto>(falseDto, HttpStatus.OK);
    }


    ///Phani

    @PostMapping("/addGoogle")
    public ResponseEntity<?> addGoogleProfile(@RequestBody GoogleDto googleDto){
        User user = new User();
        BeanUtils.copyProperties(googleDto, user);
        User userCreated = userServices.save(user);
        if (userCreated != null) {
            return new ResponseEntity<StatusDTO>(new StatusDTO(true), HttpStatus.OK);
        }
        return new ResponseEntity<StatusDTO>(new StatusDTO(false), HttpStatus.BAD_REQUEST);
    }

    ///Phani

    @PostMapping("/addAddress")
    public ResponseEntity<?> addAddress(@RequestBody AddressDto addressDto){

        String email=userTokenService.getEmail(addressDto.getUuid());
        User user=userServices.getUser(email);
        user.setUserAddress(addressDto.getAddress());
        User userCreated=userServices.save(user);
        if (userCreated != null) {
            return new ResponseEntity<StatusDTO>(new StatusDTO(true), HttpStatus.OK);
        }
        return new ResponseEntity<StatusDTO>(new StatusDTO(false), HttpStatus.BAD_REQUEST);

    }

    ///Phani
    @PostMapping("/addFacebook")
    public ResponseEntity<?> addFacebook(@RequestBody FacebookDto facebookDto){
        String Id=null;
        if(facebookDto.getIdToken()==null){
            return new ResponseEntity<StatusDTO>(new StatusDTO(false), HttpStatus.BAD_REQUEST);
        }
        String accessToken=facebookDto.getIdToken();
        FacebookInputDto facebookInputDTO = (new RestTemplate()).getForObject("https://graph.facebook.com/me?fields=name,email,picture&access_token="+accessToken, FacebookInputDto.class);
        User user=new User();
        user.setUserName(facebookInputDTO.getName());
        user.setUserEmail(facebookInputDTO.getEmail());
        user.setUserImgUrl(facebookInputDTO.getPicture().getData().getUrl());
        User userCreated=userServices.save(user);
        if (userCreated != null) {
            return new ResponseEntity<StatusDTO>(new StatusDTO(true), HttpStatus.OK);
        }
        return new ResponseEntity<StatusDTO>(new StatusDTO(false), HttpStatus.BAD_REQUEST);
    }

}

