package kavya.sample.testapplication.presenters;

import junit.framework.Assert;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import kavya.sample.categorylibrary.model.CategoryDataModel;
import kavya.sample.categorylibrary.model.ICategoryDataModel;
import kavya.sample.testapplication.fragments.WelcomeFragment;
import kavya.sample.testapplication.pojo.ImageItem;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class WelcomePresenter extends BasePresenter {

    @NonNull
    private List<ImageItem> mCategoryList = new ArrayList<>();

    @NonNull
    private ICategoryDataModel mCategoryDataModel;

    public WelcomePresenter(Context context) {
        super();
        mCategoryDataModel = CategoryDataModel.getInstance(context);
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
        return mCategoryDataModel.isFirstTimeUser();
    }

    public String getImageUrl() {
        // TODO: get most clicked category
        int index = mCategoryDataModel.getMostClickedCategoryIndex();
        return mCategoryList.get(index).imageUrl();
    }
}
