package kavya.sample.testapplication.fragments;

import junit.framework.Assert;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import kavya.sample.testapplication.MainRecyclerViewAdapter;
import kavya.sample.testapplication.R;
import kavya.sample.testapplication.pojo.ImageItem;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class CategoriesFragment extends Fragment {

    private MainRecyclerViewAdapter mAdapter;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.categories_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeRecyclerView((RecyclerView) view.findViewById(R.id.categories_recycler_view));
    }

    private void initializeRecyclerView(@NonNull RecyclerView recyclerView) {
        mAdapter = new MainRecyclerViewAdapter(getContext());

        Assert.assertNotNull(recyclerView);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
    }

    // TODO: update images
    public void updateImages(@NonNull List<ImageItem> imageItems) {
        mAdapter.updateItems(imageItems);
    }
}
