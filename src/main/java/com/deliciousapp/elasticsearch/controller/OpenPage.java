package com.deliciousapp.elasticsearch.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OpenPage {

    // inject via application.properties
    @Value("${welcome.message:test}")
    private String message = "Hello World";

    @RequestMapping("/welcome")
    public String welcome(Map<String, Object> model) {

        InetAddress ip;
        String hostname;
        try {
            ip = InetAddress.getLocalHost();
            hostname = ip.getHostName();
            System.out.println("Your current IP address : " + ip);
            System.out.println("Your current Hostname : " + hostname);

        } catch (UnknownHostException e) {

            e.printStackTrace();
        }

        model.put("message", this.message);
        return "welcome";
    }

    @RequestMapping("/index")
    public String open(Map<String, Object> model) {

//        InetAddress ip;
//        String hostname;
//        try {
//            ip = InetAddress.getLocalHost();
//            hostname = ip.getHostName();
//            System.out.println("Your current IP address : " + ip);
//            System.out.println("Your current Hostname : " + hostname);
//
//        } catch (UnknownHostException e) {
//
//            e.printStackTrace();
//        }

        model.put("message", this.message);
        return "index";
    }

}