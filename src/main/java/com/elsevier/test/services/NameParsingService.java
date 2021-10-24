package com.elsevier.test.services;

import com.elsevier.test.exceptions.TooFewNamesException;
import org.json.JSONArray;
import org.json.JSONObject;

public interface NameParsingService {

    JSONObject normalisePersonName(String person) throws TooFewNamesException;

    JSONArray normalisePersonListNames(String personList);
}
