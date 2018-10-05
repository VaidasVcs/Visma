package com.example.taskjsonimages.activities;

import android.content.Intent;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.taskjsonimages.Image;
import com.example.taskjsonimages.ImageLoadingAsyncTask;
import com.example.taskjsonimages.LocalJsonParserImpl;
import com.example.taskjsonimages.R;
import com.example.taskjsonimages.interfaces.ImageItemListener;
import com.example.taskjsonimages.interfaces.ImageItemLoader;
import com.example.taskjsonimages.interfaces.LocalJasonParser;
import com.example.taskjsonimages.recyclerview.RecyclerViewAdapter;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements ImageItemListener, ImageItemLoader {

    private ImageLoadingAsyncTask mImageLoadingAsyncTask;

    @Override
    public void onImageClicked(String url) {
        startImageActivity(url);
    }

    @Override
    public void loadImage(ImageView imageView, String url) {
        Picasso.get().load(url).into(imageView);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startAsyncTask();
        setRecyclerView(getImageList());
    }

    private void setRecyclerView(List<Image> imageList) {
        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(imageList, this, this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    private void startImageActivity(String url) {
        Intent intent = new Intent(this, ImageActivity.class);
        intent.putExtra("URL", url);
        startActivity(intent);
    }

    private void startAsyncTask() {
        if(getInternetConnectionStatus()) {
            AssetManager assetManager = getAssets();
            LocalJasonParser localJsonParser = new LocalJsonParserImpl();
            mImageLoadingAsyncTask = new ImageLoadingAsyncTask(localJsonParser, assetManager,
                    "dog_urls.json");
            mImageLoadingAsyncTask.execute();
        } else {
            Toast.makeText(this, "No internet connection", Toast.LENGTH_SHORT).show();
        }

    }

    private List<Image> getImageList() {
        try {
            if(mImageLoadingAsyncTask != null){
                return  mImageLoadingAsyncTask.get();
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean getInternetConnectionStatus() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if (networkInfo != null) {
               return networkInfo.getState() == NetworkInfo.State.CONNECTED;
            }
        }
        return false;
    }
}