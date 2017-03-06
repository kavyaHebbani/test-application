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
import kavya.sample.testapplication.presenters.CategoriesPresenter;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class CategoriesFragment extends Fragment {

    private MainRecyclerViewAdapter mAdapter;

    CategoriesPresenter mPresenter;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new CategoriesPresenter();
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

        mPresenter.bind(this);
        initializeRecyclerView((RecyclerView) view.findViewById(R.id.categories_recycler_view));
    }

    private void initializeRecyclerView(@NonNull RecyclerView recyclerView) {
        mAdapter = new MainRecyclerViewAdapter(getContext(), getFragmentManager());

        Assert.assertNotNull(recyclerView);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        if (false) {
            manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(final int position) {
                    switch (position) {
                        case 0:
                            return 2;
                        case 5:
                            return 3;
                        default:
                            return 1;
                    }
                }
            });
        }
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(mAdapter);
    }

    public void updateImages(@NonNull List<ImageItem> imageItems) {
        mAdapter.updateItems(imageItems);
    }

    @Override
    public void onDestroyView() {
        mPresenter.unbind();

        super.onDestroyView();
    }
}
