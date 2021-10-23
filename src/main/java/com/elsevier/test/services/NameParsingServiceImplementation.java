package com.elsevier.test.services;

import com.elsevier.test.exceptions.TooFewNamesException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Service
public class NameParsingServiceImplementation implements NameParsingService {

    public static void main(String[] args) throws TooFewNamesException {
        NameParsingServiceImplementation implementation = new NameParsingServiceImplementation();
        String name = "Peter H. Kristensen";
        JSONObject object = implementation.parsePersonNames(name);
        System.out.println("object = " + object.toString());
    }


    @Override
    public JSONObject parsePersonNames(String person) throws TooFewNamesException {
        return new JSONObject(disambiguatePerson(person));
    }

    @Override
    public JSONArray parsePersonListNames(String personList) throws TooFewNamesException {
        String[] personNames = personList.split(",");
        JSONArray jasonArray = new JSONArray();
        for (String person : personNames) {
            JSONObject object = new JSONObject(disambiguatePerson(person));
            jasonArray.put(object);

        }
        return jasonArray;
    }

    private Map<String, String> disambiguatePerson(String person) throws TooFewNamesException {
        Map<String, String> map = new HashMap<>();

        String[] personNames = person.split(" ");
        if (personNames.length  <= 1)
            throw new TooFewNamesException("Person has too few names to be complete");

        if (person.contains(",")){
            arrangeNameInverse(map, personNames, person.indexOf(","));
        }else arrangeName(map, personNames);

        return map;
    }

    private void arrangeNameInverse(Map<String, String> map, String[] personNames, int separator) {
        map.put("lastName", String.join(" ",Arrays.copyOfRange(personNames, 0, separator)));
        map.put("firstName", String.join(" ",Arrays.copyOfRange(personNames, separator, personNames.length)));
    }

    private void arrangeName(Map<String, String> map, String[] personNames) {
        map.put("firstName", String.join(" ",Arrays.copyOf(personNames, personNames.length-1)));
        map.put("lastName", personNames[personNames.length-1]);
    }

}
