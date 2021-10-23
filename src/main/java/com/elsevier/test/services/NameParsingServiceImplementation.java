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


    @Override
    public JSONObject parsePersonNames(String person) throws TooFewNamesException {
        return new JSONObject(disambiguatePerson(person));
    }

    @Override
    public JSONArray parsePersonListNames(String personList) {
        String[] personNames = personList.split(",");
        JSONArray jasonArray = new JSONArray();
        for (String person : personNames) {
            JSONObject object = null;
            try {
                object = new JSONObject(disambiguatePerson(person));
            } catch (TooFewNamesException e) {
                e.printStackTrace();
            }
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
            String[] inverseNames = person.split(",");
            arrangeNameInverse(map, inverseNames[0], inverseNames[1]);
        }else arrangeName(map, personNames);

        return map;
    }

    private void arrangeNameInverse(Map<String, String> map, String lastName, String firstName) {
        map.put("firstName", firstName.trim());
        map.put("lastName", lastName.trim());
    }

    private void arrangeName(Map<String, String> map, String[] personNames) {
        map.put("firstName", String.join(" ",Arrays.copyOf(personNames, personNames.length-1)).trim());
        map.put("lastName", personNames[personNames.length-1].trim());
    }

}
