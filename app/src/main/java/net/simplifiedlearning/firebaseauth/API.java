package net.simplifiedlearning.firebaseauth;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
/**
 * Created by krabb_000 on 18-04-2018.
 */

public interface API {

    String BASE_URL = "http://birdobservationservice.azurewebsites.net/Service1.svc/";

    @GET("observations")
    Call<List<Birds>> getBirds();
}
