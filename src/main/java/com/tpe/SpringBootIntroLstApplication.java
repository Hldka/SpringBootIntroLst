package com.tpe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication// bütün lazim olan annotationlar icinde gizli , boulerlatt code'lerden kurtariyor
// ben bunu run ettimede compile ettigimde bu code olmayacak , bu arka planda calisacak
public class SpringBootIntroLstApplication {

    public static void main(String[] args) {// bunlar otomatik geliyor
        SpringApplication.run(SpringBootIntroLstApplication.class, args);
    }

}
