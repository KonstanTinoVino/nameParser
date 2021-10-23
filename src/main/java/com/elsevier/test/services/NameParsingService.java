package com.elsevier.test.services;

import org.json.JSONObject;

public interface NameParsingService {

    JSONObject parsePersonNames(String person);

    JSONObject parsePersonListNames(String personList);
}
