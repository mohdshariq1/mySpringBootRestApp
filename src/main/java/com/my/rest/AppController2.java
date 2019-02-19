package com.my.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@RestController
public class AppController2 {

	@Autowired
	private AppService service;
	@Autowired
	private AppModelResourceAssembler assembler;

//	@GetMapping(path = "/test" , produces={"application/json"})
	@RequestMapping(value = "/test", produces={"application/json"})
	@Transactional
	//	org.springframework.dao.InvalidDataAccessApiUsageException: You're trying to execute a streaming query method without a surrounding transaction that keeps the connection open so that the Stream can actually be consumed. Make sure the code consuming the stream uses @Transactional or any other way of declaring a (read-only) transaction.
	
	public Resource<AppModel> getKey() {
         AppModel model = service.findById(1L).orElseThrow(() -> new RuntimeException("Not found"));
         System.out.println("3>>>>>>>>" + model);
         try (Stream<AppModel> stream = service.findCompany("jpmc")) {
        	 System.out.println("Query>>>>>>>>");
             stream.forEach(x -> System.out.println ("Query >>>>>>>>" +x));
         }
         return assembler.toResource(model);
    }
	
	@RequestMapping(value = "/company", produces={"application/json"})
	@Transactional
	//	org.springframework.dao.InvalidDataAccessApiUsageException: You're trying to execute a streaming query method without a surrounding transaction that keeps the connection open so that the Stream can actually be consumed. Make sure the code consuming the stream uses @Transactional or any other way of declaring a (read-only) transaction.
	public  String getAllByCompany(@RequestParam String name) {
          List<AppModel> countList = service.findByCompany(name) ;
         
      // create a new Gson instance
          Gson gson = new GsonBuilder().setPrettyPrinting().create();
          String jsonCartList = gson.toJson(countList);
          JsonParser jp = new JsonParser();
          JsonElement je = jp.parse(jsonCartList);
          String prettyJsonString = gson.toJson(je);
          System.out.println("jsonCartList: ??" + prettyJsonString);
          return prettyJsonString;
          
    }
	
	
	
	


	
	@RequestMapping(value = "/count", produces={"application/json"})
	@Transactional
	//	org.springframework.dao.InvalidDataAccessApiUsageException: You're trying to execute a streaming query method without a surrounding transaction that keeps the connection open so that the Stream can actually be consumed. Make sure the code consuming the stream uses @Transactional or any other way of declaring a (read-only) transaction.
	//http://localhost:8080/baeldung/company?name=gwg
	public  String getCount() {
         AppModel model = service.findById(1L).orElseThrow(() -> new RuntimeException("Not found"));
         AppModel model2 = service.findById(2L).orElseThrow(() -> new RuntimeException("Not found"));
          System.out.println("3>>>>>>>>" + model);
         try (Stream<AppModel> stream = service.findCompany("jpmc")) {
        	 System.out.println("Query>>>>>>>>");
             stream.forEach(x -> System.out.println ("Query >>>>>>>>" +x));
         }
         List<AppModel> countList = new ArrayList<AppModel>();
        
         countList.add(model);
         countList.add(model2);
         // convert your list to json
        
      // create a new Gson instance
         Gson gson = new GsonBuilder().setPrettyPrinting().create();
         String jsonCartList = gson.toJson(countList);
           // convert your list to json
         // print your generated json
         
         JsonParser jp = new JsonParser();
         JsonElement je = jp.parse(jsonCartList);
         String prettyJsonString = gson.toJson(je);
         System.out.println("jsonCartList: ??" + prettyJsonString);
         
         
         return prettyJsonString;
    }
	

	       


	 
	@RequestMapping(value = "/post", method = RequestMethod.POST)
    public ResponseEntity < String > persistPerson(@Valid @RequestBody String name, Errors errors) {
		 System.out.println("name: ??" + name.substring(1, name.length()-1));
		    List<AppModel> countList = service.findByCompany(name.substring(1, name.length()-1)) ;
	         
		          // create a new Gson instance
		          Gson gson = new GsonBuilder().setPrettyPrinting().create();
		          String jsonCartList = gson.toJson(countList);
		          JsonParser jp = new JsonParser();
		          JsonElement je = jp.parse(jsonCartList);
		          String prettyJsonString = gson.toJson(je);
		          System.out.println("jsonCartList: ??" + prettyJsonString);
		          
		          
        return ResponseEntity.ok(prettyJsonString);
    }
	
}
