package kavya.sample.testapplication.presenters;

import android.support.annotation.NonNull;

import java.util.List;

import kavya.sample.categorylibrary.model.ICategoryDataModel;
import kavya.sample.testapplication.network.ApiService;
import kavya.sample.testapplication.pojo.ImageItem;
import kavya.sample.testapplication.utils.ISchedulerProvider;
import kavya.sample.testapplication.utils.SchedulerProvider;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ksreeniv on 06/03/17.
 */

public abstract class BasePresenter {

    @NonNull
    ApiService mApiService;

    @NonNull
    ISchedulerProvider mSchedulerProvider;

    @NonNull
    ICategoryDataModel mCategoryDataModel;

    @NonNull
    CompositeSubscription mSubscription = new CompositeSubscription();

    BasePresenter(@NonNull ApiService apiService,
                  @NonNull ISchedulerProvider schedulerProvider,
                  @NonNull ICategoryDataModel categoryDataModel) {
        mApiService = apiService;
        mSchedulerProvider = schedulerProvider;
        mCategoryDataModel = categoryDataModel;
    }

    @NonNull
    Observable<List<ImageItem>> getCategories() {
        return mApiService.getCategories();
    }

    @NonNull
    Observable<List<ImageItem>> getImagesForCategory(@NonNull String categoryName) {
        return mApiService.getImagesForCategory(categoryName);
    }

    public void unbind() {
        mSubscription.clear();
    }
}
