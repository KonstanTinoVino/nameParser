package com.elsevier.test.services;

import com.elsevier.test.exceptions.TooFewNamesException;
import org.json.JSONArray;
import org.json.JSONObject;

public interface NameParsingService {

    JSONObject parsePersonNames(String person) throws TooFewNamesException;

    JSONArray parsePersonListNames(String personList);
}
