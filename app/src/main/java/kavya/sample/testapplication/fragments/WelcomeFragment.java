package kavya.sample.testapplication.fragments;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import kavya.sample.testapplication.R;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class WelcomeFragment extends Fragment {

    ImageView mImageView;

    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            // TODO: open categories list
        });

        mImageView = (ImageView) view.findViewById(R.id.welcome_image_view);
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

}
