package com.example.taskjsonimages.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.taskjsonimages.R;
import com.squareup.picasso.Picasso;

public class ImageActivity extends AppCompatActivity {

    ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mImageView = findViewById(R.id.clicked_image);
        loadImage();
    }

    private String getStringExtra() {
        Intent intent = getIntent();
        return intent.getStringExtra("URL");
    }

    private void loadImage() {
        Picasso.get().load(getStringExtra()).into(mImageView);
    }
}