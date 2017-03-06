package kavya.sample.testapplication.fragments;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import kavya.sample.testapplication.R;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class ItemDetailFragment extends Fragment {

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.item_detail_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView imageView = (ImageView) view.findViewById(R.id.detail_image_view);

        //TODO: get category image
        Picasso.with(getContext())
               .load(R.mipmap.ic_launcher)
               .fit()
               .placeholder(R.mipmap.ic_launcher)
               .error(R.mipmap.ic_launcher)
               .into(imageView);
    }
}
