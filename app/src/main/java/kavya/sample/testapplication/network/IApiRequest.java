package kavya.sample.testapplication.network;

import kavya.sample.testapplication.pojo.ImageData;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ksreeniv on 06/03/17.
 */

public interface IApiRequest {

    String AUTHORIZATION
            = "Authorization: Basic YjU5ZmQ4NDQxZTYxODc2MTBiM2E6ZmI5YzE5NzI5YjY5ZGFkMDUxZDJjZmY0NGI4M2E3Yjk4ZTc4NDYyOA";

    @GET("v2/images/search")
    @Headers(AUTHORIZATION)
    Observable<ImageData> fetchImages(@Query("per_page") int pageSize,
                                      @Query("page") int pageNumber,
                                      @Query("view") String view,
                                      @Query("query") String search);
}
