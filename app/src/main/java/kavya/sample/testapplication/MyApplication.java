package kavya.sample.testapplication;

import android.app.Application;
import android.support.annotation.NonNull;

import kavya.sample.categorylibrary.model.CategoryDataModel;
import kavya.sample.categorylibrary.model.ICategoryDataModel;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class MyApplication extends Application {

    @NonNull
    public ICategoryDataModel getCategoryDataModel() {
        return CategoryDataModel.getInstance(getApplicationContext());
    }
}
