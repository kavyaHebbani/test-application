package kavya.sample.testapplication.presenters;

import junit.framework.Assert;

import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.util.Log;

import kavya.sample.categorylibrary.model.ICategoryDataModel;
import kavya.sample.testapplication.fragments.CategoriesFragment;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class CategoriesPresenter extends BasePresenter {

    public CategoriesPresenter(ICategoryDataModel categoryDataModel) {
        super(categoryDataModel);
    }

    public void bind(@NonNull CategoriesFragment fragment) {
        Assert.assertNotNull(fragment);

        mSubscription.add(getCategories()
                                  .subscribeOn(mSchedulerProvider.computation())
                                  .observeOn(mSchedulerProvider.mainThread())
                                  .subscribe(fragment::updateImages,
                                             err -> Log.e(getClass().getName(),
                                                          "Error updating Data:" + err)));
    }

    public boolean isNewUser() {
        return mCategoryDataModel.isFirstTimeUser();
    }

    public Pair<Integer, Integer> getMostClickedCategoryIndex() {
        return mCategoryDataModel.getMostClickedCategoryIndex();
    }

}
