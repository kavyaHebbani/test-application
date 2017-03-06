package kavya.sample.testapplication.presenters;

import junit.framework.Assert;

import android.support.annotation.NonNull;
import android.util.Log;

import kavya.sample.testapplication.fragments.CategoriesFragment;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class CategoriesPresenter extends BasePresenter {

    @NonNull
    private CompositeSubscription mSubscription = new CompositeSubscription();

    public CategoriesPresenter() {
        super();
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

    public void unbind() {
        mSubscription.clear();
    }
}
