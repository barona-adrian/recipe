package com.deliciousapp.hbase.utils;

import com.deliciousapp.exceptions.EmailAddressException;
import com.deliciousapp.hbase.connection.HBaseConnection;
import com.deliciousapp.hbase.model.Response;
import com.deliciousapp.hbase.model.signup.EmailSignUp;
import com.deliciousapp.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;


@Slf4j
@RequestScope
@Component
@Service
public class SignUpUtils {

    private HTable table;
    @Autowired
    CommonUtils utils;

    @Value("${profile.column.family}") private String profileColumnFamily;


    public SignUpUtils(@Autowired HBaseConnection hBaseConnection){
        table = hBaseConnection.getTable();
    }

    public Response signup(EmailSignUp emailSignUp) throws IOException, IllegalAccessException {
        String email = emailSignUp.getEmail();
        if(!emailExists(email)) {
            insertEmail(emailSignUp);
            return new Response("success");
        }
        else {
            throw new EmailAddressException("Given email address already exists.");
        }
    }

    private boolean emailExists(String email) throws IOException {
        Get get = new Get(Bytes.toBytes(email));
        Result result = table.get(get);
        return !result.isEmpty();
    }

    private void insertEmail(EmailSignUp emailSignUp) throws IllegalAccessException, IOException {
        utils.dynamicInsertIntoHBase(table, emailSignUp.getEmail(), EmailSignUp.class, emailSignUp, profileColumnFamily);
    }

}
