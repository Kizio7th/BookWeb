package com.example.demo.model.dto;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EntityResponse {
    public static ResponseEntity<Object> content(String message, HttpStatus status, Object resObject) {

        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("TimeStamp", new Date());
        map.put("Message", message);
        map.put("Status", status.value());
        map.put("Data", resObject);

        return new ResponseEntity<Object>(map, status);
    }

}
