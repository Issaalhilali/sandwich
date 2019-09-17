package com.issa.sandwich;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.issa.sandwich.model.sandwich;
import com.issa.sandwich.utils.JsonUtil;
import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    public static com.issa.sandwich.model.sandwich sandwich = null;

    ImageView ImageDeisplay;

    TextView name;
    TextView AlsoKnow;

    TextView placeOfOriginDisplay;

    TextView descriptionDisplay;

    TextView ingredeintsDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ImageDeisplay = (ImageView) findViewById(R.id.image_display);
        name = (TextView) findViewById(R.id.text_display);
        AlsoKnow = (TextView) findViewById(R.id.know_display);
        placeOfOriginDisplay = (TextView) findViewById(R.id.origin_display);
        descriptionDisplay = (TextView) findViewById(R.id.description_display);
        ingredeintsDisplay = (TextView) findViewById(R.id.ingre_display);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        try {
            sandwich = JsonUtil.parseSandwichJson(json);
            Log.v("SandwichMainNAme_log2", sandwich.getMainName());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null){
            closeOnError();
            return;
        }
        populateUi();

    }

    private void populateUi() {
        name.setText("- " +sandwich.getMainName());
        descriptionDisplay.setText(sandwich.getDescription());
        Log.v("sandwichImage",sandwich.getImage());
        Picasso.with(this).load(sandwich.getImage()).placeholder(R.drawable.ic_launcher_background).into(ImageDeisplay);
        Log.v("sandwichImage2",sandwich.getImage());
        if(sandwich.getPlaceOfOrigin()!=null) {
            placeOfOriginDisplay.setText(sandwich.getPlaceOfOrigin());
        }
        ArrayList ingredients = (ArrayList) sandwich.getIngredients();
        if (ingredients != null) {
            for (int i = 0; i < ingredients.size(); i++) {
                ingredeintsDisplay.append("- " + ingredients.get(i).toString());
                ingredeintsDisplay.append("\n" + '\n');
            }
        }
        ArrayList otherNames = (ArrayList) sandwich.getAlsoKnownAs();
        if (otherNames != null) {
            for (int i = 0; i < otherNames.size(); i++)
                AlsoKnow.append("- " + otherNames.get(i).toString());
            AlsoKnow.append("\n" + '\n');
        }
    }



    private void closeOnError() {
        finish();

        Toast.makeText(this, R.string.details_error_message, Toast.LENGTH_SHORT).show();
    }

}
