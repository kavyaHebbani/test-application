package kavya.sample.testapplication;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kavya.sample.testapplication.fragments.MyFragmentManager;
import kavya.sample.testapplication.pojo.ImageItem;

import static junit.framework.Assert.assertNotNull;
import static kavya.sample.testapplication.fragments.MyFragmentManager.LISTING_FRAGMENT;
import static kavya.sample.testapplication.presenters.ListingPresenter.CATEGORY_POSITION;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class MainRecyclerViewAdapter
        extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder> {

    @NonNull
    private Context mContext;

    @NonNull
    private FragmentManager mFragmentManager;

    @NonNull
    private List<ImageItem> mItems = new ArrayList<>();

    public MainRecyclerViewAdapter(@NonNull Context context,
                                   @NonNull FragmentManager fragmentManager) {
        assertNotNull(context);

        mContext = context;
        mFragmentManager = fragmentManager;
    }

    public void updateItems(List<ImageItem> item) {
        mItems.addAll(item);
        notifyDataSetChanged();
    }

    public void rearrangeItems(Pair<Integer, Integer> pair) {
        if (mItems.size() > 0) {
            if (pair.first != -1) {
                Collections.swap(mItems, pair.first, 5);
            }
            if (pair.second != -1) {
                Collections.swap(mItems, pair.second, 0);
            }
            notifyDataSetChanged();
        }
    }

    @Override
    public MainRecyclerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.recycler_image_item, parent, false);
        return new MainRecyclerViewHolder(mFragmentManager, view);
    }

    @Override
    public void onBindViewHolder(final MainRecyclerViewHolder holder, final int position) {
        if (mItems.size() == 0) {
            return;
        }
        Picasso.with(mContext)
               .load(mItems.get(position).imageUrl())
               .fit()
               .placeholder(R.mipmap.ic_launcher)
               .error(R.mipmap.ic_launcher)
               .into(holder.mImageView);

        holder.setClickListener(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class MainRecyclerViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private FragmentManager mFragmentManager;

        @NonNull
        ImageView mImageView;

        MainRecyclerViewHolder(@NonNull FragmentManager fragmentManager, final View itemView) {
            super(itemView);
            mFragmentManager = fragmentManager;
            mImageView = (ImageView) itemView.findViewById(R.id.image_item);
        }

        void setClickListener(int position) {
            mImageView.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt(CATEGORY_POSITION, position);
                MyFragmentManager manager = new MyFragmentManager(mFragmentManager);
                manager.goToFragmentWithExtra(LISTING_FRAGMENT, bundle);
            });
        }
    }
}
