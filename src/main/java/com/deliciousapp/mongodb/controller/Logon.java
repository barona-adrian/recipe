//package com.deliciousapp.mongodb.controller;
//
//
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//
//import com.mongodb.BasicDBObject;
//import com.mongodb.BulkWriteOperation;
//import com.mongodb.BulkWriteResult;
//import com.mongodb.Cursor;
//import com.mongodb.DB;
//import com.mongodb.DBCollection;
//import com.mongodb.DBCursor;
//import com.mongodb.DBObject;
//import com.mongodb.MongoClient;
//import com.mongodb.ParallelScanOptions;
//import com.mongodb.ServerAddress;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Set;
//
//import static java.util.concurrent.TimeUnit.SECONDS;
//
//@Slf4j
//@RestController
//@Path("/mongodb")
//@Produces({"application/json"})
//@Consumes({"application/json"})
//@RequestMapping(value = "/elasticsearch/ingredients")
//@CrossOrigin
//public class Logon {
//
//    MongoClient mongoClient = new MongoClient(Arrays.asList(new ServerAddress("localhost", 27017),
//            new ServerAddress("localhost", 27018),
//            new ServerAddress("localhost", 27019)));
//
//    @POST
//    @Path("/signup")
//    @Produces({"application/json"})
//    @Consumes({"application/json"})
//    @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> ingredientInquiry(
//            @Valid @RequestBody String request) throws Exception {
//
//
//
//
//
//
//
//
//        return new ResponseEntity("", HttpStatus.OK);
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//
//
//
//
//
//
//
//
//
//    }
//
//
//
//}
