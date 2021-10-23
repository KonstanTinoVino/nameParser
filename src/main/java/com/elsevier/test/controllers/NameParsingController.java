package com.elsevier.test.controllers;

import com.elsevier.test.exceptions.TooFewNamesException;
import com.elsevier.test.services.NameParsingService;
import org.json.JSONArray;
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

    private final NameParsingService service;

    @Autowired
    public NameParsingController(NameParsingService service) {
        this.service = service;
    }

    @GetMapping("/normalisePersonName")
    public ResponseEntity<String> normalisePersonName(@RequestParam String person){
        try {
            return new ResponseEntity<>(service.parsePersonNames(person).toString(), HttpStatus.OK);
        } catch (TooFewNamesException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new JSONObject().toString(), HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/normalisePersonListNames")
    public ResponseEntity<String> normalisePersonListNames(@RequestParam String personList){
        JSONArray array = service.parsePersonListNames(personList);

        if (!array.isEmpty())
        return new ResponseEntity<>(service.parsePersonListNames(personList).toString(), HttpStatus.OK);

        return new ResponseEntity<>(new JSONArray().toString(), HttpStatus.NOT_ACCEPTABLE);
    }
}
