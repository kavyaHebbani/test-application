package kavya.sample.testapplication;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import kavya.sample.testapplication.fragments.MyFragmentManager;
import kavya.sample.testapplication.pojo.ImageItem;

import static junit.framework.Assert.assertNotNull;
import static kavya.sample.testapplication.fragments.MyFragmentManager.ITEM_DETAIL_FRAGMENT;
import static kavya.sample.testapplication.fragments.MyFragmentManager.LISTING_FRAGMENT;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class ListingRecyclerViewAdapter
        extends RecyclerView.Adapter<ListingRecyclerViewAdapter.MainRecyclerViewHolder> {

    @NonNull
    private Context mContext;

    @NonNull
    private FragmentManager mFragmentManager;

    @NonNull
    private List<ImageItem> mItems = new ArrayList<>();

    public ListingRecyclerViewAdapter(@NonNull Context context,
                                      @NonNull FragmentManager fragmentManager) {
        assertNotNull(context);

        mContext = context;
        mFragmentManager = fragmentManager;
    }

    public void updateItems(List<ImageItem> item) {
        mItems.addAll(item);
        notifyDataSetChanged();
    }

    @Override
    public MainRecyclerViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.recycler_image_item, parent, false);
        return new MainRecyclerViewHolder(view);
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

        holder.mImageView.setOnClickListener(v -> {
            //TODO open listing item fragment with picture and title
            MyFragmentManager manager = new MyFragmentManager(mFragmentManager);
            manager.goToFragment(LISTING_FRAGMENT, ITEM_DETAIL_FRAGMENT);
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    static class MainRecyclerViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;

        MainRecyclerViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_item);
        }
    }
}
