package com.example.taskjsonimages;

import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.example.taskjsonimages.interfaces.LocalJasonParser;

import java.util.List;

public class ImageLoadingAsyncTask extends AsyncTask<Void, Void, List<Image>> {

    private LocalJasonParser mLocalJasonParser;
    private AssetManager mAssetManager;
    private String mLocalJsonFile;

    public ImageLoadingAsyncTask(LocalJasonParser localJasonParser, AssetManager assetManager,
                                 String localJsonFile) {
        mLocalJasonParser = localJasonParser;
        mAssetManager = assetManager;
        mLocalJsonFile = localJsonFile;
    }

    @Override
    protected List<Image> doInBackground(Void... voids) {
       String json = mLocalJasonParser.loadJsonFromAsset(mAssetManager,mLocalJsonFile);
       return mLocalJasonParser.getPopulatedImageList(json);
    }
}