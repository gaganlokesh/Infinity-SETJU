package com.setju.android.infinity;

import android.graphics.drawable.Drawable;

/**
 * Created by Gagan Lokesh on 20-03-2018.
 */

public class Category {

    int mCategory;
    int image;
    Drawable drawable;

    Category(int category, int Image, Drawable mDrawable ){
    mCategory = category;
    image = Image;
    drawable = mDrawable;
    }

    public int getmCategory(){
        return mCategory;
    }

    public int getImage(){
    return image;
    }

    public Drawable getImageDrawable(){
        return drawable;
    }


    public void setImage(int image) {
        this.image = image;
    }
}
