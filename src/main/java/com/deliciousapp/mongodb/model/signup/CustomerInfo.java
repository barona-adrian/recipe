package com.deliciousapp.mongodb.model.signup;



import java.time.LocalDateTime;

@lombok.Getter
@lombok.Setter
@lombok.ToString
@lombok.AllArgsConstructor
@lombok.NoArgsConstructor
public class CustomerInfo {


    String emailAddress;
    String password;
    String firstName;
    String lastName;


}
