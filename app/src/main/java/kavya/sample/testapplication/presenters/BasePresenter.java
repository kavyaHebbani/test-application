package kavya.sample.testapplication.presenters;

import android.support.annotation.NonNull;

import java.util.List;

import kavya.sample.testapplication.network.ApiService;
import kavya.sample.testapplication.pojo.ImageItem;
import kavya.sample.testapplication.utils.ISchedulerProvider;
import kavya.sample.testapplication.utils.SchedulerProvider;
import rx.Observable;

/**
 * Created by ksreeniv on 06/03/17.
 */

public abstract class BasePresenter {

    @NonNull
    ApiService mApiService;

    @NonNull
    ISchedulerProvider mSchedulerProvider;

    BasePresenter() {
        mApiService = ApiService.getInstance();
        mSchedulerProvider = SchedulerProvider.getInstance();
    }

    @NonNull
    Observable<List<ImageItem>> getCategories() {
        return mApiService.getCategories();
    }

}
