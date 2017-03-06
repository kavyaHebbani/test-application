package kavya.sample.testapplication;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import kavya.sample.testapplication.pojo.ImageItem;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by ksreeniv on 06/03/17.
 */

public class MainRecyclerViewAdapter
        extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder> {

    @NonNull
    private Context mContext;

    @NonNull
    private List<ImageItem> mItems = new ArrayList<>();

    public MainRecyclerViewAdapter(@NonNull Context context) {
        assertNotNull(context);

        mContext = context;
    }

    public void updateItems(List<ImageItem> items) {
        mItems.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public MainRecyclerViewHolder onCreateViewHolder(final ViewGroup parent,
                                                     final int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                                  .inflate(R.layout.recycler_image_item, parent, false);
        return new MainRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MainRecyclerViewHolder holder,
                                 final int position) {
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
        ImageView mImageView;

        MainRecyclerViewHolder(final View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.image_item);
        }

        void setClickListener(int position) {
            mImageView.setOnClickListener(v -> {
                // TODO: Open listing for the category
            });
        }
    }
}
