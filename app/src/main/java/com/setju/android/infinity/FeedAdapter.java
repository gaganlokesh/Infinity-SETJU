package com.setju.android.infinity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan Lokesh on 18-03-2018.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder>{

    private static List<Feed> mFeedData;
    private Context mContext;

    FeedAdapter(Context context, ArrayList<Feed> feed){
        mFeedData = feed;
        mContext = context;

    }

    @Override
    public FeedAdapter.FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FeedViewHolder(LayoutInflater.from(mContext).inflate(R.layout.feed_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(FeedAdapter.FeedViewHolder holder, int position) {

        Feed currentFeed = mFeedData.get(position);

        String title = currentFeed.getTitle();
        String body = currentFeed.getBody();
        String time = currentFeed.getTime();
        String date = currentFeed.getDate();

        holder.feedTitle.setText(title);
        holder.feedBody.setText(body);
        holder.feedTime.setText(time);
        holder.feedDate.setText(date);

    }

    @Override
    public int getItemCount() {
        return mFeedData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    void clear(){
        int size = mFeedData.size();
        mFeedData.clear();
        notifyItemRangeRemoved(0, size);
    }

    void addAll(List<Feed> feeds){
        int size = mFeedData.size();
        mFeedData.addAll(feeds);
        notifyItemRangeInserted(0, size);
        notifyDataSetChanged();
    }

    void add(Feed feed){
        int size = mFeedData.size();
        mFeedData.add(feed);
        notifyItemInserted(size);
        notifyItemRangeChanged(0, size);
        notifyDataSetChanged();

    }

    class FeedViewHolder extends RecyclerView.ViewHolder{

        TextView feedTitle;
        TextView feedBody;
        TextView feedTime;
        TextView feedDate;

        public FeedViewHolder(View itemView) {
            super(itemView);

            feedTitle = itemView.findViewById(R.id.feed_title);
            feedBody = itemView.findViewById(R.id.feed_body);
            feedDate = itemView.findViewById(R.id.feed_date);
            feedTime = itemView.findViewById(R.id.feed_time);
        }
    }
}
