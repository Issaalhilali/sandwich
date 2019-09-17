package com.issa.sandwich.utils;

import com.issa.sandwich.model.sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtil {

    public static final String OBJECT_NAME = "name";
    public static final String MAIN_NAME = "mainName";
    public static final String ALSO_KNOWN_AS = "alsoKnownAs";
    public static final String PLACE_OF_ORIGIN = "placeOfOrigin";
    public static final String DESCRIPTION = "description";
    public static final String IMAGE = "image";
    public static final String INGREDIENTS = "ingredients";
    public static ArrayList<String> knownASList;
    public static ArrayList<String> ingredientsList;
    public static sandwich sandwich;


    public static sandwich parseSandwichJson(String json) throws JSONException {


        try {
            knownASList = new ArrayList<String>();
            ingredientsList = new ArrayList<String>();
            JSONObject jsonObject= new JSONObject(json);
            JSONObject mainObject = jsonObject.getJSONObject(OBJECT_NAME);
            String main_name = mainObject.getString(MAIN_NAME);
            JSONArray asloKnownAS = (JSONArray) mainObject.get(ALSO_KNOWN_AS);
            String palceOfOrigin = jsonObject.getString(PLACE_OF_ORIGIN);
            String description = jsonObject.getString(DESCRIPTION);
            String image = jsonObject.getString(IMAGE);
            JSONArray ingredents = (JSONArray) jsonObject.get(INGREDIENTS);
            getList(asloKnownAS, knownASList);
            getList(ingredents, ingredientsList);
            sandwich = new sandwich(main_name , knownASList, palceOfOrigin, description, image, ingredientsList);


        }catch (JSONException e){
            e.printStackTrace();
        }
        return sandwich;
    }

    public static ArrayList getList(JSONArray jsonArray, ArrayList list)throws JSONException{
        if (jsonArray != null){
            for (int j =0; j< jsonArray.length(); j++){
                list.add(jsonArray.getString(j));
            }
        }
        return list;
    }
}
