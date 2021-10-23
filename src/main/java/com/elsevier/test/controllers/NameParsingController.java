package com.elsevier.test.controllers;

import com.elsevier.test.services.NameParsingService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/parsing")
public class NameParsingController {

    private NameParsingService service;

    @Autowired
    public NameParsingController(NameParsingService service) {
        this.service = service;
    }

    @GetMapping("/normalisePersonName")
    public ResponseEntity<JSONObject> normalisePersonName(@RequestParam String person){
        return new ResponseEntity<>(service.parsePersonNames(person), HttpStatus.OK);
    }

    @GetMapping("/normalisePersonListNames")
    public ResponseEntity<JSONObject> normalisePersonListNames(@RequestParam String personList){
        return new ResponseEntity<>(service.parsePersonListNames(personList), HttpStatus.OK);
    }
}