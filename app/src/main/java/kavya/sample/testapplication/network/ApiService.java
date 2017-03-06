package kavya.sample.testapplication.network;

import android.support.annotation.NonNull;

import java.util.List;

import kavya.sample.testapplication.pojo.ImageItem;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by ksreeniv on 06/03/17.
 */

public final class ApiService {

    private static final String CLIENT_ID = "cc8eda3d67155fda58f8";

    private static final String CLIENT_SECRET = "2d88458a248813fca7da997eeecabf01275acb6c";

    private static final String BASE_URL = "https://" + CLIENT_ID + ":" + CLIENT_SECRET
                                           + "@api.shutterstock.com/";

    private static final int NUMBER_OF_CATEGORIES = 6;

    private static final int NUMBER_OF_IMAGES = 12;

    @NonNull
    private IApiRequest mApiRequest;

    private static ApiService mInstance;

    private ApiService(@NonNull IApiRequest apiRequest) {
        mApiRequest = apiRequest;
    }

    public static ApiService getInstance() {
        if (mInstance == null) {
            mInstance = new ApiService(getApiRequest());
        }
        return mInstance;
    }

    @NonNull
    public Observable<List<ImageItem>> getCategories() {
        return mApiRequest.fetchImages(NUMBER_OF_CATEGORIES, 1, "full", "Android")
                          .compose(new ImageDataToPreviewTransformer());
    }

    @NonNull
    public Observable<List<ImageItem>> getImagesForCategory(String query) {
        return mApiRequest.fetchImages(NUMBER_OF_IMAGES, 1, "full", query)
                          .compose(new ImageDataToPreviewTransformer());
    }

    @NonNull
    private static IApiRequest getApiRequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getOkHttpClient())
                .build();

        return retrofit.create(IApiRequest.class);
    }

    @NonNull
    private static OkHttpClient getOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient
                .Builder()
                .addInterceptor(interceptor)
                .build();
    }
}
