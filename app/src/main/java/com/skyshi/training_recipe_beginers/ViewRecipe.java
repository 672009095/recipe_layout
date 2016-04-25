package com.skyshi.training_recipe_beginers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

/**
 * Created by skyshi on 22/04/16.
 */
public class ViewRecipe extends Fragment{
    ImageView img_view_recipe;
    TextView txt_view_namerecipe,txt_view_type,txt_view_price,
            txt_view_place,txt_view_mainIngredient,txt_view_ingredient,
            txt_view_tools,txt_view_step;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_view_recipe,container,false);
        Bundle bundle = this.getArguments();
        img_view_recipe = (ImageView)v.findViewById(R.id.img_view_recipe);
        Glide.with(this).load(bundle.getString("image"))
                .centerCrop()
                .placeholder(R.drawable.noimage)
                .into(img_view_recipe);
        txt_view_namerecipe = (TextView)v.findViewById(R.id.txt_view_title);
        txt_view_namerecipe.setText(bundle.getString("name"));

        txt_view_type = (TextView)v.findViewById(R.id.txt_view_type);
        txt_view_type.setText(bundle.getString("type"));

        txt_view_price = (TextView)v.findViewById(R.id.txt_view_price);
        txt_view_price.setText(bundle.getString("price"));

        txt_view_place = (TextView)v.findViewById(R.id.txt_view_place);
        txt_view_place.setText(bundle.getString("place"));

        txt_view_mainIngredient = (TextView)v.findViewById(R.id.txt_view_mainingredients);
        txt_view_mainIngredient.setText(bundle.getString("mainIngredient"));

        txt_view_ingredient = (TextView)v.findViewById(R.id.txt_view_ingredients);
        txt_view_ingredient.setText(bundle.getString("ingredient"));

        txt_view_tools = (TextView)v.findViewById(R.id.txt_view_tools);
        txt_view_tools.setText(bundle.getString("tools"));

        txt_view_step = (TextView)v.findViewById(R.id.txt_view_step);
        txt_view_step.setText(bundle.getString("step"));
        return v;
    }
}
