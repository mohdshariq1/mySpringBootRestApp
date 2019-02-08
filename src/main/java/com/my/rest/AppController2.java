package com.my.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

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
	
	public  String getAllByCompany() {
        /* AppModel model = service.findById(1L).orElseThrow(() -> new RuntimeException("Not found"));
         AppModel model2 = service.findById(2L).orElseThrow(() -> new RuntimeException("Not found"));
        *///  System.out.println("3>>>>>>>>" + model);
          List<AppModel> countList = service.findByCompany("gwg") ;
         
         
           
      // create a new Gson instance
         Gson gson = new Gson();
         // convert your list to json
         String jsonCartList = gson.toJson(countList);
         // print your generated json
         System.out.println("jsonCartList: " + jsonCartList);
         
         
         return jsonCartList;
    }
	
	


	
	@RequestMapping(value = "/count", produces={"application/json"})
	@Transactional
	//	org.springframework.dao.InvalidDataAccessApiUsageException: You're trying to execute a streaming query method without a surrounding transaction that keeps the connection open so that the Stream can actually be consumed. Make sure the code consuming the stream uses @Transactional or any other way of declaring a (read-only) transaction.
	
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
         
      // create a new Gson instance
         Gson gson = new Gson();
         // convert your list to json
         String jsonCartList = gson.toJson(countList);
         // print your generated json
         System.out.println("jsonCartList: " + jsonCartList);
         
         
         return jsonCartList;
    }
	
	
}
