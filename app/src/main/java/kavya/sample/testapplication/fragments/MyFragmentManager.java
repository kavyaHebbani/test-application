package kavya.sample.testapplication.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class MyFragmentManager {

    public static final String WELCOME_FRAGMENT = "WELCOME_FRAGMENT";
    public static final String CATEGORIES_FRAGMENT = "CATEGORIES_FRAGMENT";
    public static final String LISTING_FRAGMENT = "LISTING_FRAGMENT";
    public static final String ITEM_DETAIL_FRAGMENT = "ITEM_DETAIL_FRAGMENT";

    @NonNull
    private FragmentManager mFragmentManager;

    public MyFragmentManager(@NonNull FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public void goToFragment(@NonNull String from, @NonNull String to) {
        goToFragment(from, to, null);
    }

    public void goToFragment(@NonNull String from, @NonNull String to, Bundle bundle) {
        View containerView = mFragmentManager.findFragmentByTag(from).getView();
        if (containerView != null) {
            int containerId = ((ViewGroup) containerView.getParent()).getId();
            showFragment(getFragment(to, bundle), containerId, to);
        }
    }

    public void showFragment(@NonNull Fragment fragment, int containerId, @NonNull String tag) {
        mFragmentManager
                .beginTransaction()
                .replace(containerId, fragment, tag)
                .addToBackStack(tag)
                .commit();
    }

    @NonNull
    private Fragment getFragment(@NonNull String name, Bundle bundle) {
        Fragment fragment = new WelcomeFragment();
        switch (name) {
            case CATEGORIES_FRAGMENT:
                fragment = new CategoriesFragment();
                break;
            case LISTING_FRAGMENT:
                fragment = new ListingFragment();
                break;
            case ITEM_DETAIL_FRAGMENT:
                fragment = new ItemDetailFragment();
                break;
            default:
                break;
        }

        if (bundle != null) {
            fragment.setArguments(bundle);
        }
        return fragment;
    }
}
