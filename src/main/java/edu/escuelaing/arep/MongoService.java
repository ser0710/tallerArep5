package edu.escuelaing.arep;

import org.json.JSONObject;

public interface MongoService {
    void create(String string);

    JSONObject getString();
}
