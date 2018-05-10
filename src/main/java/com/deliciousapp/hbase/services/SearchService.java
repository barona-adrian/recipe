package com.deliciousapp.hbase.services;

import com.deliciousapp.elasticsearch.model.findbyingredient.input.FindByIngredientsRequest;
import com.deliciousapp.hbase.model.Response;
import com.deliciousapp.hbase.model.login.EmailLogIn;
import com.deliciousapp.hbase.model.signup.EmailSignUp;
import com.deliciousapp.hbase.utils.LogInUtils;
import com.deliciousapp.hbase.utils.SignUpUtils;
import com.deliciousapp.hbase.utils.StoreSearchUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Scope(value="singleton")
public class SearchService {

    @Autowired
    StoreSearchUtils storeSearchUtils;

    public Response storeSearch(FindByIngredientsRequest request) {

        return storeSearchUtils.storeSearchByIP(request);
    }



}
