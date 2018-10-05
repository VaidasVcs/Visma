package com.example.taskjsonimages.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.taskjsonimages.Image;
import com.example.taskjsonimages.R;
import com.example.taskjsonimages.interfaces.ImageItemListener;
import com.example.taskjsonimages.interfaces.ImageItemLoader;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<ImageViewHolder> {

    private List<Image> mImageList;
    private ImageItemLoader mImageItemLoader;
    private ImageItemListener mImageItemListener;

    public RecyclerViewAdapter(List<Image> imageList, ImageItemLoader imageItemLoader,
                               ImageItemListener imageItemListener) {
        mImageList = imageList;
        mImageItemLoader = imageItemLoader;
        mImageItemListener = imageItemListener;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_item_image,parent,false);
        ImageViewHolder imageViewHolder = new ImageViewHolder(view, new ImageViewModel());
        imageViewHolder.setImageItemListener(mImageItemListener);
        imageViewHolder.setImageItemLoader(mImageItemLoader);
        return imageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.onBind(mImageList.get(position));
    }

    @Override
    public int getItemCount() {
        return (mImageList != null) ? mImageList.size() : 0;
    }
}