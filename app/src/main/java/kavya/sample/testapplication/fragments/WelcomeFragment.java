package kavya.sample.testapplication.fragments;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import kavya.sample.testapplication.R;
import kavya.sample.testapplication.presenters.WelcomePresenter;

import static kavya.sample.testapplication.fragments.MyFragmentManager.CATEGORIES_FRAGMENT;
import static kavya.sample.testapplication.fragments.MyFragmentManager.WELCOME_FRAGMENT;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class WelcomeFragment extends Fragment {

    ImageView mImageView;

    WelcomePresenter mPresenter;

    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPresenter = new WelcomePresenter(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.welcome_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button button = (Button) view.findViewById(R.id.welcome_button_text);
        button.setOnClickListener(v -> {
            MyFragmentManager manager = new MyFragmentManager(getFragmentManager());
            manager.goToFragment(WELCOME_FRAGMENT, CATEGORIES_FRAGMENT);
        });

        mImageView = (ImageView) view.findViewById(R.id.welcome_image_view);

        mPresenter.bind(this);
    }

    // TODO get image url
    public void updateImage(String url) {
        if (!url.isEmpty()) {
            Picasso.with(getContext())
                   .load(url)
                   .fit()
                   .placeholder(R.mipmap.ic_launcher)
                   .error(R.mipmap.ic_launcher)
                   .into(mImageView);
        }
    }

    public void updateFragment() {
        Log.e("Kavya", "First time user");
        MyFragmentManager manager = new MyFragmentManager(getFragmentManager());
        manager.goToFragment(WELCOME_FRAGMENT, CATEGORIES_FRAGMENT);
    }

    @Override
    public void onDestroyView() {
        mPresenter.unbind();

        super.onDestroyView();
    }

}
