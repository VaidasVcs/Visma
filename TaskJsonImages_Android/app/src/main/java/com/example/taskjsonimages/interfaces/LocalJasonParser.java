package com.example.taskjsonimages.interfaces;

import android.content.res.AssetManager;

import com.example.taskjsonimages.Image;

import java.util.List;

public interface LocalJasonParser {

    String loadJsonFromAsset(AssetManager assetManager, String fileName);

    List<Image> getPopulatedImageList(String json);
}
