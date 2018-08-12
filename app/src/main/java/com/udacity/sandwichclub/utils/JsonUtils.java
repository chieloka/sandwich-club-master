package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich = new Sandwich();
        try {
            //create a new json object
            JSONObject jsonSandwich = new JSONObject(json);

            //parse the json and extract required data
            JSONObject name = jsonSandwich.getJSONObject("name");
            String mainName = name.getString("mainName");
            JSONArray arrAlsoKnownAs = name.getJSONArray("alsoKnownAs");
            JSONArray arrIngredients = jsonSandwich.getJSONArray("ingredients");

            String description = jsonSandwich.getString("description");
            String placeOfOrigin = jsonSandwich.getString("placeOfOrigin");
            String image = jsonSandwich.getString("image");

            List<String> listAlsoKnownAs = new ArrayList<String>();
            for(int i = 0; i < arrAlsoKnownAs.length(); i++){
                listAlsoKnownAs.add(arrAlsoKnownAs.getString(i));
            }

            List<String> listIngredients = new ArrayList<String>();
            for(int i = 0; i < arrIngredients.length(); i++){
                listIngredients.add(arrIngredients.getString(i));
            }



            sandwich.setMainName(mainName);
            sandwich.setAlsoKnownAs(listAlsoKnownAs);
            sandwich.setDescription(description);
            sandwich.setImage(image);
            sandwich.setIngredients(listIngredients);
            sandwich.setPlaceOfOrigin(placeOfOrigin);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
