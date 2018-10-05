package com.example.taskjsonimages.recyclerview;

import com.example.taskjsonimages.Image;

public class ImageViewModel {

    private Image mImage;

    ImageViewModel() {

    }

    String getImageUrl() {
        return mImage.getUrl();
    }

    public void setImage(Image mImage) {
        this.mImage = mImage;
    }
}