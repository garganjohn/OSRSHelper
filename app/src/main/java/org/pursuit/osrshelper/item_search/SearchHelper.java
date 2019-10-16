package org.pursuit.osrshelper.item_search;

import android.content.Context;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.pursuit.osrshelper.network.GEModels;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SearchHelper {
    public static final String TAG = "SearchHelper userQuery";

    private Context context;
    //private String itemToBeSearched;
    private JSONObject itemAssets;

    public SearchHelper(Context context) {
        this.context = context;
       // this.itemToBeSearched = itemToBeSearched;
    }

    private String loadItemsFromAssets(Context context) {
        String json = null;
        try {
            InputStream is = context.getApplicationContext().getAssets().open("allitems.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public List<Integer> userQuery(String itemToBeSearched) {
        List<Integer> potentialItems = new ArrayList<>();

        try {
            itemAssets = new JSONObject(loadItemsFromAssets(context));
            JSONArray itemArr = itemAssets.getJSONArray("allitems");

            for (int i = 0; i < itemArr.length(); i++) {
                JSONObject insideObj = itemArr.getJSONObject(i);
                if (insideObj.has("name")) {
                    if (insideObj.getString("name").toUpperCase().contains(itemToBeSearched.toUpperCase())) {
                        potentialItems.add(insideObj.getInt("id"));
                    }
                }

            }
        } catch (JSONException jse) {
            jse.printStackTrace();
            jse.getCause();
            return null;
        }
        return potentialItems;
    }


}
