package com.deliciousapp.hbase.model.signup;

@lombok.Getter
@lombok.Setter
@lombok.AllArgsConstructor
public class EmailSignUp {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
}
