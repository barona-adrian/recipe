package com.deliciousapp.mongodb.controller;


import com.google.gson.GsonBuilder;
import com.mongodb.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

@Slf4j
@RestController
@Path("/mongodb")
@Produces({"application/json"})
@Consumes({"application/json"})
@RequestMapping(value = "/elasticsearch/ingredients")
@CrossOrigin
public class Logon {



    @POST
    @Path("/signup")
    @Produces({"application/json"})
    @Consumes({"application/json"})
    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> ingredientInquiry(
            @Valid @RequestBody String request) throws Exception {








        return new ResponseEntity("", HttpStatus.OK);
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        MongoClient mongoClient = new MongoClient(Arrays.asList(
                new ServerAddress("node30437-recipet7mongo.njs.jelastic.vps-host.net", 27017),
                new ServerAddress("node30436-recipet7mongo.njs.jelastic.vps-host.net", 27017)));

        DB db = mongoClient.getDB( "deliciousapp" );

        DBCollection coll = db.getCollection("testCollections");

        latch.countDown();
        BasicDBObject doc = new BasicDBObject("name", "MongoDB")
                .append("type", "database")
                .append("count", 1)
                .append("info", new BasicDBObject("x", 203).append("y", 102));
        coll.insert(doc);



        DBObject myDoc = coll.findOne();
        System.out.println(myDoc);

        latch.await();


    }



}
