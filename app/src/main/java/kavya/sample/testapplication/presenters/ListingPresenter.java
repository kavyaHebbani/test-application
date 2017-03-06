package kavya.sample.testapplication.presenters;

import junit.framework.Assert;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;

import kavya.sample.categorylibrary.model.CategoryDataModel;
import kavya.sample.categorylibrary.model.ICategoryDataModel;
import kavya.sample.testapplication.fragments.ListingFragment;
import rx.Observable;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class ListingPresenter extends BasePresenter {

    public static final String CATEGORY_POSITION = "category_position";

    @NonNull
    private ICategoryDataModel mCategoryDataModel;

    public ListingPresenter(@NonNull Context context) {
        mCategoryDataModel = CategoryDataModel.getInstance(context);
    }

    public void bind(@NonNull ListingFragment fragment) {
        Assert.assertNotNull(fragment);

        int index = getCategoryIndex(fragment);

        mSubscription
                .add(getImagesForCategory(mCategoryDataModel.getCategoryName(index))
                             .subscribeOn(mSchedulerProvider.computation())
                             .observeOn(mSchedulerProvider.mainThread())
                             .subscribe(fragment::updateImages,
                                        err -> Log.e(getClass().getName(),
                                                     "Error updating Data:" + err)));

        mSubscription.add(Observable.just(index)
                                    .observeOn(mSchedulerProvider.computation())
                                    .subscribe(it -> mCategoryDataModel.categoryClicked(it),
                                               err -> Log.e(getClass().getName(),
                                                            "Error updating Data:" + err)));
    }

    private int getCategoryIndex(@NonNull Fragment fragment) {
        int index = 0;
        Bundle bundle = fragment.getArguments();
        if (bundle != null) {
            index = bundle.getInt(CATEGORY_POSITION, 0);
        }
        return index;
    }
}
