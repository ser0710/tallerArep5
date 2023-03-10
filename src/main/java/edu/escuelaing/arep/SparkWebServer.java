package edu.escuelaing.arep;

import edu.escuelaing.arep.service.MongoServiceImpl;

import static spark.Spark.*;

public class SparkWebServer {

    static MongoService mongoService = MongoServiceImpl.getInstance();
    public static void main(String... args){
        port(getPort());
        get("hello/:String", (req,res) ->
        {mongoService.create(req.params("String"));
        return mongoService.getString();
        });

    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 6000;
    }

}
