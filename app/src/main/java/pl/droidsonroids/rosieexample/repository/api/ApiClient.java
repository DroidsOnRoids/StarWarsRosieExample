package pl.droidsonroids.rosieexample.repository.api;

import pl.droidsonroids.rosieexample.model.People;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Query;

public class ApiClient {

    private static final String API_URL = "http://swapi.co/api/";

    public interface SwapiClient {
        //default limit is 10
        @GET("/people/")
        People getPeople(@Query("page") int page);
    }

    public static SwapiClient create() {
        RestAdapter restAdapter =
                new RestAdapter.Builder().setEndpoint(API_URL).setLogLevel(RestAdapter.LogLevel.FULL).build();
        return restAdapter.create(SwapiClient.class);
    }
}
