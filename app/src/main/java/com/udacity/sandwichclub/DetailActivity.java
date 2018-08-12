package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

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
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        TextView originTv = findViewById(R.id.origin_tv);
        String placeOfOrigin = sandwich.getPlaceOfOrigin();
        if(placeOfOrigin.equalsIgnoreCase("")){
            originTv.setText(" Unknown");
        }else{
            originTv.setText(" "+placeOfOrigin);
        }


        TextView descriptionTv = findViewById(R.id.description_tv);
        descriptionTv.setText(sandwich.getDescription());

        TextView ingredientsTv = findViewById(R.id.ingredients_tv);
        List<String> ingredientList = sandwich.getIngredients();
        for(int i=0;i < ingredientList.size();i++){
            if(i == 0){
                ingredientsTv.append(ingredientList.get(i));
            }else{
                ingredientsTv.append(", "+ingredientList.get(i));
            }

        }

        TextView alsoKnownAsTv = findViewById(R.id.also_known_tv);
        List<String> alsoKnownAsList = sandwich.getAlsoKnownAs();

        if(alsoKnownAsList.size() == 0){
            alsoKnownAsTv.setText(" None available");
        }else{
            for(int i=0;i < alsoKnownAsList.size();i++){
                if(i == 0){
                    alsoKnownAsTv.append(alsoKnownAsList.get(i));
                }else{
                    alsoKnownAsTv.append(", "+alsoKnownAsList.get(i));
                }

            }
        }


    }
}
