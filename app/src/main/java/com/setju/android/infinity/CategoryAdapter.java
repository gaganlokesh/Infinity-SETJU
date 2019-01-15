package com.setju.android.infinity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gagan Lokesh on 20-03-2018.
 */
//public class CategoryAdapter extends ArrayAdapter<Category>  {


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

//    private Context mContext;
//    private ArrayList<Category> mCategory;
//
//    public CategoryAdapter(Context context, ArrayList<Category> categories){
//        super(context, 0, categories);
//        mContext = context;
//        mCategory = categories;
//    }

//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        View listItem = convertView;
//
//        if(listItem==null){
//            listItem = LayoutInflater.from(getContext()).inflate(R.layout.category_list_item, parent,false);
//        }
//
//        Category currentCategory = getItem(position);
//
//        TextView Title = listItem.findViewById(R.id.category_tv2);
//        Title.setText(currentCategory.getmCategory());
//
//        ImageView imageView = listItem.findViewById(R.id.category_imageView);
//        imageView.setImageDrawable(currentCategory.getImage());
//
//        return listItem;
//    }

        Context mContext;
    List<Category> mCategory;


    public CategoryAdapter(Context context, ArrayList<Category> categories){
    mContext = context;
    mCategory = categories;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(mContext).inflate(R.layout.category_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder,final int position) {

        Category currentCategory = mCategory.get(position);


        int Title = currentCategory.getmCategory();
        int image = currentCategory.getImage();
        Drawable drawable = currentCategory.getImageDrawable();

        holder.mTitleTextView.setText(Title);
        holder.mImageView.setImageDrawable(drawable);


    }

    void add(Category category){
        int size = mCategory.size();
        mCategory.add(category);
        notifyItemInserted(size);
//        notifyItemRangeChanged(0, size);
//        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return mCategory.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTitleTextView;
        ImageView mImageView;


        public CategoryViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = itemView.findViewById(R.id.category_tv2);
            mImageView = itemView.findViewById(R.id.category_imageView);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Category currentCategory = mCategory.get(getAdapterPosition());

            Intent intent = new Intent(mContext, EventListActivity.class);
            intent.putExtra(Constants.CATEGORY_NAME, currentCategory.getmCategory());
            intent.putExtra(Constants.CATEGORY_IMAGE, currentCategory.getImage());

            mContext.startActivity(intent);
        }
    }
}
