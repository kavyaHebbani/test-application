package kavya.sample.testapplication.presenters;

import junit.framework.Assert;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kavya.sample.testapplication.fragments.WelcomeFragment;
import kavya.sample.testapplication.network.ApiService;
import kavya.sample.testapplication.pojo.ImageItem;
import kavya.sample.testapplication.utils.ISchedulerProvider;
import kavya.sample.testapplication.utils.SchedulerProvider;
import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class WelcomePresenter {

    @NonNull
    private ApiService mApiService;

    @NonNull
    private ISchedulerProvider mSchedulerProvider;

    @NonNull
    private List<ImageItem> mCategoryList = new ArrayList<>();

    @NonNull
    private CompositeSubscription mSubscription = new CompositeSubscription();

    public WelcomePresenter() {
        mApiService = ApiService.getInstance();
        mSchedulerProvider = SchedulerProvider.getInstance();
    }

    @NonNull
    private Observable<List<ImageItem>> getCategories() {
        return mApiService.getCategories();
    }

    public void bind(@NonNull WelcomeFragment fragment) {
        Assert.assertNotNull(fragment);

        mSubscription.add(getCategories()
                                  .subscribeOn(mSchedulerProvider.computation())
                                  .observeOn(mSchedulerProvider.computation())
                                  .subscribe(it -> {
                                                 mCategoryList.addAll(it);
                                                 fragment.updateImage(getImageUrl());
                                             },
                                             err -> Log.e(getClass().getName(),
                                                          "Error updating Data:" + err)));
    }

    public boolean isNewUser() {
        // TODO: get real data
        return true;
    }

    public String getImageUrl() {
        // TODO: get most clicked category
        int index = 0;
        return mCategoryList.get(index).imageUrl();
    }

    public void unbind() {
        mSubscription.clear();
    }
}
