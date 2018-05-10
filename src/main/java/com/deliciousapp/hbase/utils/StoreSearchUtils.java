package com.deliciousapp.hbase.utils;

import com.deliciousapp.elasticsearch.model.findbyingredient.input.FindByIngredientsRequest;
import com.deliciousapp.elasticsearch.model.findbyingredient.input.Ingredient;
import com.deliciousapp.exceptions.HBaseException;
import com.deliciousapp.exceptions.PasswordAuthorizationException;
import com.deliciousapp.hbase.connection.HBaseConnection;
import com.deliciousapp.hbase.model.Response;
import com.deliciousapp.hbase.model.login.EmailLogIn;
import com.deliciousapp.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequestScope
@Component
@Service
public class StoreSearchUtils {

    private HTable table;
    @Autowired
    CommonUtils utils;

    @Value("${profile.column.family}") private String profileColumnFamily;


    public StoreSearchUtils(@Autowired HBaseConnection hBaseConnection){
        table = hBaseConnection.getTable();
    }

    public Response storeSearchByIP(FindByIngredientsRequest request) {
        String ip = request.getIpAddress();
        List<Ingredient> ingredientList = request.getIngredientList();
        Put put = new Put(Bytes.toBytes(ip));
        List<String> ingredients = new ArrayList<>();
        for(Ingredient ingredient : ingredientList){
            ingredients.add(ingredient.getName());
        }
        String ingredientsCommaSeperated = String.join(",", ingredients);
        put.addColumn(Bytes.toBytes(profileColumnFamily), Bytes.toBytes("ingredients"), Bytes.toBytes(ingredientsCommaSeperated));
        return new Response("success");
    }




}
