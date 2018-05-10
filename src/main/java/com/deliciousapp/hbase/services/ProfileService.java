package com.deliciousapp.hbase.services;

import com.deliciousapp.hbase.model.Response;
import com.deliciousapp.hbase.model.login.EmailLogIn;
import com.deliciousapp.hbase.model.signup.EmailSignUp;
import com.deliciousapp.hbase.utils.LogInUtils;
import com.deliciousapp.hbase.utils.SignUpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Scope(value="singleton")
public class ProfileService {

    @Autowired
    SignUpUtils signUpUtils;

    @Autowired
    LogInUtils logInUtils;

    public Response signup(EmailSignUp emailSignUp) throws IOException, IllegalAccessException {
        return signUpUtils.signup(emailSignUp);
    }

    public Response authorizeLogin(EmailLogIn emailLogIn) throws IOException, IllegalAccessException {
        return logInUtils.authorizeLogin(emailLogIn);
    }



}
