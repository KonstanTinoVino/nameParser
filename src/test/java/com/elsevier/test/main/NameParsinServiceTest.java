package com.elsevier.test.main;

import com.elsevier.test.exceptions.TooFewNamesException;
import com.elsevier.test.services.NameParsingService;
import com.elsevier.test.services.NameParsingServiceImplementation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class NameParsinServiceTest {

    private NameParsingService service;

    @BeforeEach
    void initialize(){
        this.service = new NameParsingServiceImplementation();
    }


    @Test
    void testSinglePersonParsing() throws TooFewNamesException, JSONException {
        String name = "John Doe";
        JSONObject object = service.normalisePersonName(name);
        JSONAssert.assertEquals("{\"firstName\":\"John\",\"lastName\":\"Doe\"}", object, false);

        name = "Doe, John";
        object = service.normalisePersonName(name);
        JSONAssert.assertEquals("{\"firstName\":\"John\",\"lastName\":\"Doe\"}", object, false);

        name = "Hans-Christian Jensen";
        object = service.normalisePersonName(name);
        JSONAssert.assertEquals("{\"firstName\":\"Hans-Christian\",\"lastName\":\"Jensen\"}", object, false);

        name = "H-C Jensen";
        object = service.normalisePersonName(name);
        JSONAssert.assertEquals("{\"firstName\":\"H-C\",\"lastName\":\"Jensen\"}", object, false);


        name = "P. H. Kristensen";
        object = service.normalisePersonName(name);
        JSONAssert.assertEquals("{\"firstName\":\"P. H.\",\"lastName\":\"Kristensen\"}", object, false);

        name = "Kristensen, P. H.";
        object = service.normalisePersonName(name);
        JSONAssert.assertEquals("{\"firstName\":\"P. H.\",\"lastName\":\"Kristensen\"}", object, false);

        name = "Peter Hans Kristensen";
        object = service.normalisePersonName(name);
        JSONAssert.assertEquals("{\"firstName\":\"Peter Hans\",\"lastName\":\"Kristensen\"}", object, false);

        name = "Peter H. Kristensen";
        object = service.normalisePersonName(name);
        JSONAssert.assertEquals("{\"firstName\":\"Peter H.\",\"lastName\":\"Kristensen\"}", object, false);
    }

    @Test
    void testMultiPersonParsing() throws JSONException {
        String names = "H-C Jensen, Peter Hans Kristensen, John Doe";
        JSONArray object = service.normalisePersonListNames(names);
        JSONAssert.assertEquals("[{\"firstName\":\"H-C\",\"lastName\":\"Jensen\"},{\"firstName\":\"Peter Hans\",\"lastName\":\"Kristensen\"},{\"firstName\":\"John\",\"lastName\":\"Doe\"}]", object, false);
    }

}
