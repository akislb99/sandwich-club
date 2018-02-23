package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        String mainName;
        List<String> alsoKnownAsList = new ArrayList<>();
        String placeOfOrigin;
        String description;
        String image;
        List<String> ingredientsList = new ArrayList<>();

        try {
            JSONObject jsonObject = new JSONObject(json);

            JSONObject name = jsonObject.getJSONObject("name");
            mainName = name.getString("mainName");

            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            if (alsoKnownAsArray != null) {
                int len = alsoKnownAsArray.length();
                for (int i=0; i<len; i++){
                    alsoKnownAsList.add(alsoKnownAsArray.get(i).toString());
                }
            }

            placeOfOrigin = jsonObject.getString("placeOfOrigin");
            description = jsonObject.getString("description");
            image = jsonObject.getString("image");

            JSONArray ingredientsArray = jsonObject.getJSONArray("ingredients");
            if (ingredientsArray != null) {
                int len = ingredientsArray.length();
                for (int i=0; i<len; i++){
                    ingredientsList.add(ingredientsArray.get(i).toString());
                }
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);

    }
}
