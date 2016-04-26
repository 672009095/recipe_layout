package com.skyshi.training_recipe_beginers;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

/**
 * Created by skyshi on 22/04/16.
 */
public class ViewRecipe extends AppCompatActivity{
    ImageView img_view_recipe;
    TextView txt_view_namerecipe,txt_view_type,txt_view_price,
            txt_view_place,txt_view_mainIngredient,txt_view_ingredient,
            txt_view_tools,txt_view_step;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_recipe);
        Bundle bundle = getIntent().getExtras();
        img_view_recipe = (ImageView)findViewById(R.id.img_view_recipe);
        Glide.with(this).load(bundle.getString("image"))
                .asBitmap()
                .centerCrop()
                .placeholder(R.drawable.noimage)
                .into(new BitmapImageViewTarget(img_view_recipe){
                    @Override
                    protected void setResource(Bitmap resource) {
                        RoundedBitmapDrawable roundedCircle = RoundedBitmapDrawableFactory.create(getResources(),resource);
                        roundedCircle.setCircular(true);
                        img_view_recipe.setImageDrawable(roundedCircle);
                    }
                });
        txt_view_namerecipe = (TextView)findViewById(R.id.txt_view_title);
        txt_view_namerecipe.setText(bundle.getString("name"));

        txt_view_type = (TextView)findViewById(R.id.txt_view_type);
        txt_view_type.setText(bundle.getString("type"));

        txt_view_price = (TextView)findViewById(R.id.txt_view_price);
        txt_view_price.setText(bundle.getString("price"));

        txt_view_place = (TextView)findViewById(R.id.txt_view_place);
        txt_view_place.setText(bundle.getString("place"));

        txt_view_mainIngredient = (TextView)findViewById(R.id.txt_view_mainingredients);
        txt_view_mainIngredient.setText(bundle.getString("mainIngredient"));

        txt_view_ingredient = (TextView)findViewById(R.id.txt_view_ingredients);
        txt_view_ingredient.setText(bundle.getString("ingredient"));

        txt_view_tools = (TextView)findViewById(R.id.txt_view_tools);
        txt_view_tools.setText(bundle.getString("tools"));

        txt_view_step = (TextView)findViewById(R.id.txt_view_step);
        txt_view_step.setText(bundle.getString("step"));
    }
}
