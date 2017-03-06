package kavya.sample.testapplication.presenters;

import junit.framework.Assert;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import kavya.sample.testapplication.fragments.CategoriesFragment;
import kavya.sample.testapplication.network.ApiService;
import kavya.sample.testapplication.pojo.ImageItem;
import kavya.sample.testapplication.utils.ISchedulerProvider;
import kavya.sample.testapplication.utils.SchedulerProvider;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class CategoriesPresenter {

    @NonNull
    private ApiService mApiService;

    @NonNull
    private ISchedulerProvider mSchedulerProvider;

    @NonNull
    private CompositeSubscription mSubscription = new CompositeSubscription();

    public CategoriesPresenter() {
        mApiService = ApiService.getInstance();
        mSchedulerProvider = SchedulerProvider.getInstance();
    }

    public void bind(@NonNull CategoriesFragment fragment) {
        Assert.assertNotNull(fragment);

        mSubscription.add(getCategories()
                                  .subscribeOn(mSchedulerProvider.computation())
                                  .observeOn(mSchedulerProvider.mainThread())
                                  .subscribe(fragment::updateImages,
                                             err -> Log.e(getClass().getName(),
                                                          "Error updating Data:"
                                                          + err)));
    }

    @NonNull
    private Observable<List<ImageItem>> getCategories() {
        return mApiService.getCategories();
    }

    public void unbind() {
        mSubscription.clear();
    }
}
