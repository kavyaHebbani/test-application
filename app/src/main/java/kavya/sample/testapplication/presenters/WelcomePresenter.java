package kavya.sample.testapplication.presenters;

import junit.framework.Assert;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kavya.sample.categorylibrary.model.ICategoryDataModel;
import kavya.sample.testapplication.fragments.WelcomeFragment;
import kavya.sample.testapplication.network.ApiService;
import kavya.sample.testapplication.pojo.ImageItem;
import kavya.sample.testapplication.utils.ISchedulerProvider;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class WelcomePresenter extends BasePresenter {

    @NonNull
    private List<ImageItem> mCategoryList = new ArrayList<>();

    public WelcomePresenter(@NonNull ApiService apiService,
                            @NonNull ISchedulerProvider schedulerProvider,
                            @NonNull ICategoryDataModel categoryDataModel) {
        super(apiService, schedulerProvider, categoryDataModel);
    }

    public void bind(@NonNull WelcomeFragment fragment) {
        Assert.assertNotNull(fragment);

        mSubscription.add(getCategories()
                                  .subscribeOn(mSchedulerProvider.computation())
                                  .doOnNext(it -> mCategoryList.addAll(it))
                                  .observeOn(mSchedulerProvider.mainThread())
                                  .subscribe(it -> fragment.updateImage(getImageUrl()),
                                             err -> Log.e(getClass().getName(),
                                                          "Error updating Data:" + err)));
    }

    public boolean isNewUser() {
        return mCategoryDataModel.isFirstTimeUser();
    }

    @NonNull
    public String getImageUrl() {
        int index = mCategoryDataModel.getMostClickedCategoryIndex().first;
        if (index == -1) {
            index = 0;
        }
        return mCategoryList.get(index).imageUrl();
    }

}
