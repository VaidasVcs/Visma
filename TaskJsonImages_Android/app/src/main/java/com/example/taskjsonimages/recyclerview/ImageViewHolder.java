package com.example.taskjsonimages.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.taskjsonimages.Image;
import com.example.taskjsonimages.R;
import com.example.taskjsonimages.interfaces.ImageItemListener;
import com.example.taskjsonimages.interfaces.ImageItemLoader;

class ImageViewHolder extends RecyclerView.ViewHolder {

    private ImageViewModel mImageViewModel;
    private ImageView mImageView;
    private ImageItemListener mImageItemListener;
    private ImageItemLoader mImageItemLoader;

    ImageViewHolder(View itemView, ImageViewModel imageViewModel) {
        super(itemView);
        mImageViewModel = imageViewModel;
        mImageView = itemView.findViewById(R.id.imageView);
        setOnImageClickListener();
    }

    void setImageItemListener(ImageItemListener mImageItemListener) {
        this.mImageItemListener = mImageItemListener;
    }

    void setImageItemLoader(ImageItemLoader mImageItemLoader) {
        this.mImageItemLoader = mImageItemLoader;
    }

    void onBind(Image image) {
        mImageViewModel.setImage(image);
        mImageItemLoader.loadImage(mImageView, mImageViewModel.getImageUrl());
    }

    private void setOnImageClickListener() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mImageItemListener.onImageClicked(mImageViewModel.getImageUrl());
            }
        });
    }
}