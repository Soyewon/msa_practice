package com.example.secondservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class SecondController {

    Environment env;

    public SecondController(Environment env){
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome Second Service";
    }

    // request header에 first-request가 있다면 로그 출력
    @GetMapping("/message")
    public String message(@RequestHeader("Second-request") String header){
        log.info(header);
        return "Hello Second Service";
    }

    // 응답해주는 포트번호 반환
    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("Server port={}", request.getServerPort());
        return String.format("Check Second Service on Port %s", env.getProperty("local.server.port"));
    }
}

