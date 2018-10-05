package com.example.taskjsonimages;

import android.content.res.AssetManager;

import com.example.taskjsonimages.interfaces.LocalJasonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LocalJsonParserImpl implements LocalJasonParser {

    public LocalJsonParserImpl() {
    }

    public String loadJsonFromAsset(AssetManager assetManager, String fileName) {
        String json;
        try {
            InputStream inputStream = assetManager.open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

        } catch (IOException  e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }

    public List<Image> getPopulatedImageList(String json) {
        List<Image> imageList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONArray urlArray = jsonObject.getJSONArray("urls");
            for(int i = 0; i < urlArray.length(); i++){
                imageList.add(new Image(urlArray.get(i).toString()));
            }
            return imageList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}