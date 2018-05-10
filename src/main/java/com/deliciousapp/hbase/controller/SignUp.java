package com.deliciousapp.hbase.controller;

import com.deliciousapp.hbase.model.Response;
import com.deliciousapp.hbase.model.signup.EmailSignUp;
import com.deliciousapp.hbase.services.ProfileService;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@RestController
@Path("/hbase")
@Produces({"application/json"})
@Consumes({"application/json"})
@RequestMapping(value = "/hbase")
@CrossOrigin
public class SignUp {

    @Autowired
    ProfileService profileService;

    @POST
    @Path("/signup")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    @RequestMapping(value = "/email_sign_up", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> emailSignUp(
            @Valid @RequestBody EmailSignUp request) throws IOException, IllegalAccessException {
        long startMillis = System.currentTimeMillis();
        log.info("Start Timestamp: " + LocalDateTime.now());
        log.info("Request:\n");
        log.info(new GsonBuilder().setPrettyPrinting().create().toJson(request));

        Response response = profileService.signup(request);

        long endMillis = System.currentTimeMillis();
        log.info("Execution Duration Time: " + (endMillis - startMillis));

        return new ResponseEntity(response, HttpStatus.OK);
    }

}
