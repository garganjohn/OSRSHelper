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
    private String itemToBeSearched;

    public SearchHelper(Context context, String itemToBeSearched) {
        this.context = context;
        this.itemToBeSearched = itemToBeSearched;
    }

    private String loadItemsFromAssets(Context context) {
        String json = null;
        try {
            InputStream is = context.getApplicationContext().getAssets().open("testitems.json");
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

    //TODO query is returning null list for potentialItems
    public List<Integer> userQuery() {
        List<Integer> potentialItems = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(loadItemsFromAssets(context));
            JSONArray itemArr = jsonObject.getJSONArray("testitems");

            for (int i = 0; i < itemArr.length(); i++) {
                JSONObject insideObj = itemArr.getJSONObject(i);
                if (insideObj.getString("name").contains(itemToBeSearched)) {
                    potentialItems.add(insideObj.getInt("id"));
                }

            }
        } catch (JSONException jse) {
            jse.printStackTrace();
            jse.getCause();
            return null;
        }
        return potentialItems;
    }
//    public List<Integer> userQuery() {
//        List<Integer> potentialItems = new ArrayList<>();
//
//
//        String jsonLocation = loadItemsFromAssets(context);
//        JSONObject jsonobject = new JSONObject(jsonLocation);
//        JSONArray jarray = (JSONArray) jsonobject.getJSONArray("allitems");
//        for (int i = 0; i < jarray.length(); i++) {
//            JSONObject jb = (JSONObject) jarray.get(i);
//            String formula = jb.getString("formule");
//            String url = jb.getString("url");
//        }
//    } catch(
//    IOException e)
//
//    {
//        e.printStackTrace();
//    } catch(
//    JSONException e)
//
//    {
//        e.printStackTrace();
//    }
//
//        return potentialItems;
//}

}
