package com.deliciousapp.hbase.utils;

import com.deliciousapp.exceptions.HBaseException;
import com.deliciousapp.exceptions.PasswordAuthorizationException;
import com.deliciousapp.hbase.connection.HBaseConnection;
import com.deliciousapp.hbase.model.Response;
import com.deliciousapp.hbase.model.login.EmailLogIn;
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
public class LogInUtils {

    private HTable table;
    @Autowired
    CommonUtils utils;

    @Value("${profile.column.family}") private String profileColumnFamily;


    public LogInUtils(@Autowired HBaseConnection hBaseConnection){
        table = hBaseConnection.getTable();
    }

    public Response authorizeLogin(EmailLogIn emailLogIn) throws IOException, IllegalAccessException {
        String email = emailLogIn.getEmail();
        String password = emailLogIn.getPassword();
        Get get = new Get(Bytes.toBytes(email));
        Result result = table.get(get);
        if(!result.isEmpty()) {
            byte[] pw = result.getValue(Bytes.toBytes(profileColumnFamily), Bytes.toBytes("password"));
            String pass = (pw!=null) ? new String(pw, "UTF-8") : null;
            if(pass!=null){
                if(password.equals(pass)){
                    return new Response("authorized");
                }
                else{
                    throw new PasswordAuthorizationException("Given password is incorrect.");
                }
            }
            else {
                throw new HBaseException("No corresponding password exists for given email address.");
            }
        }
        else {
            throw new HBaseException("Given email does not exist.");
        }
    }




}
