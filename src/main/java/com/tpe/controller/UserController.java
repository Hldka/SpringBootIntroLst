package com.tpe.controller;

import com.tpe.domain.*;
import com.tpe.dto.*;
import com.tpe.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping("/register")// ilk kayit
    public ResponseEntity<String> register(@Valid @RequestBody UserRequest userRequest){//güvenlik amacli on taraftan User user 'dan almiyorum icinde Id vat
        userService.saveUser(userRequest);
        String myResponse = "Kullanıcı kaydı başarıyla gerçekleşmiştir";

        return new ResponseEntity<>(myResponse, HttpStatus.CREATED);

    }
}
