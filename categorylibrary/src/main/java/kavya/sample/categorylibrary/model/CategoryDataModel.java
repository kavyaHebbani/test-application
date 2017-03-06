package kavya.sample.categorylibrary.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import kavya.sample.categorylibrary.database.DatabaseWrapper;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class CategoryDataModel implements ICategoryDataModel {

    private static String ICECREAMSANDWITCH = "ICECREAM";
    private static String JELLYBEAN = "JELLY";
    private static String KITKAT = "CHOCOLATE";
    private static String LOLLIPOP = "LOLLIPOP";
    private static String MARSHMALLOW = "MARSHMALLOW";
    private static String NOUGHAT = "CAKE";

    private static List<String> mCategories = Arrays.asList(
            ICECREAMSANDWITCH,
            JELLYBEAN,
            KITKAT,
            LOLLIPOP,
            MARSHMALLOW,
            NOUGHAT);

    @NonNull
    private DatabaseWrapper mDatabaseWrapper;

    private static CategoryDataModel mInstance;

    private CategoryDataModel(@NonNull DatabaseWrapper databaseWrapper) {
        mDatabaseWrapper = databaseWrapper;
        saveCategories();
    }

    @NonNull
    public static CategoryDataModel getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new CategoryDataModel(DatabaseWrapper.getInstance(context));
        }
        return mInstance;
    }

    private void saveCategories() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mDatabaseWrapper.getCategories().isEmpty()) {
                    for (String name : mCategories) {
                        mDatabaseWrapper.saveCategory(new Category(name, 0), false);
                    }
                }
            }
        }).start();
    }

    @NonNull
    @Override
    public List<String> getCategoryNames() {
        return mCategories;
    }

    @NonNull
    @Override
    public String getCategoryName(final int index) {
        return mCategories.get(index);
    }

    @Override
    public boolean isFirstTimeUser() {
        for (Category category : mDatabaseWrapper.getCategories()) {
            if (category.getNumberOfClicks() > 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Pair<Integer, Integer> getMostClickedCategoryIndex() {
        int firstIndex = -1;
        int secondIndex = -1;
        String firstName = mCategories.get(0);
        String secondName = "";
        int mostClicked = 0;

        List<Category> categories = new ArrayList<>(mDatabaseWrapper.getCategories());
        for (Category category : categories) {
            if (category.getNumberOfClicks() > mostClicked) {
                secondName = firstName;
                mostClicked = category.getNumberOfClicks();
                firstName = category.getName();
            }
        }
        if (!firstName.isEmpty()) {
            firstIndex = mCategories.indexOf(firstName);
        }
        if (!secondName.isEmpty()) {
            secondIndex = mCategories.indexOf(secondName);
        }

        return new Pair<>(firstIndex, secondIndex);
    }

    @Override
    public void categoryClicked(int index) {
        String name = getCategoryName(index);
        int clicks = mDatabaseWrapper.getCategoryNumberOfClicks(name) + 1;
        Category category = new Category(name, clicks);
        mDatabaseWrapper.saveCategory(category, true);
    }
}
