package com.skyshi.training_recipe_beginers;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
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
    FloatingActionButton fab_view_recipe;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    FloatingActionButton fab3;
    CoordinatorLayout rootLayout;
    private boolean FAB_Status = false;
    //Animations
    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;
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
        fab_view_recipe = (FloatingActionButton) findViewById(R.id.fab_view_recipe);
        fab1 = (FloatingActionButton) findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_3);

        //Animations
        show_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_show);
        hide_fab_1 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab_hide);
        show_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(getApplication(), R.anim.fab3_hide);

        fab_view_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(FAB_Status == false){
                    expandFAB();
                    FAB_Status = true;
                }else{
                    hideFAB();
                    FAB_Status = false;
                }
            }
        });
    }

    private void expandFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin += (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(show_fab_1);
        fab1.setClickable(true);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin += (int) (fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin += (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(show_fab_2);
        fab2.setClickable(true);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin += (int) (fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin += (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(show_fab_3);
        fab3.setClickable(true);
    }


    private void hideFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(hide_fab_1);
        fab1.setClickable(false);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(hide_fab_2);
        fab2.setClickable(false);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin -= (int) (fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(hide_fab_3);
        fab3.setClickable(false);
    }
}
