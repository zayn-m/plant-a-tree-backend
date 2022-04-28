package com.buildsoft.plantatree.message.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class BaseResponse {
	public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        System.out.println("Response");
        
		Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        System.out.println(map);
        return new ResponseEntity<Object>(map,status);
    }
}
