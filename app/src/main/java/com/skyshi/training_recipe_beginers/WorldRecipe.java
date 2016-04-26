package com.skyshi.training_recipe_beginers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyshi.training_recipe_beginers.Database.DatabaseHandler;
import com.skyshi.training_recipe_beginers.World.WorldObject;
import com.skyshi.training_recipe_beginers.World.WorldRecipeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyshi on 18/04/16.
 */
public class WorldRecipe extends Fragment implements ItemClickListener{
    RecyclerView rv_world_recipe;
    LinearLayoutManager llm_manager;
    WorldRecipeAdapter wra;
    List<WorldObject> worldObjectList= new ArrayList<>();
    DatabaseHandler dbHandler;
    public WorldRecipe(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_worl_recipe,container,false);
        dbHandler = new DatabaseHandler(getActivity().getBaseContext());
        rv_world_recipe = (RecyclerView)root.findViewById(R.id.rv_world_recipe);
        rv_world_recipe.setHasFixedSize(true);

        llm_manager = new LinearLayoutManager(getActivity().getBaseContext());
        rv_world_recipe.setLayoutManager(llm_manager);
        /*if(dbHandler.getAllWorldRecipe().size()==0) {
            for (int i = 0; i < 20; i++) {
                dbHandler.addWorldRecipe(new WorldObject(1, "bulu babi bakar", "fast food", "bulu babi", "bawang", "wajan", "dibakar", "10000", "japan", ""));
            }
        }*/

        worldObjectList = dbHandler.getAllWorldRecipe();
        wra = new WorldRecipeAdapter(worldObjectList,getActivity(),this);
        rv_world_recipe.setAdapter(wra);
        return root;
    }
    public void refreshListWorld(){
        worldObjectList.clear();
        worldObjectList = dbHandler.getAllWorldRecipe();
        wra = new WorldRecipeAdapter(worldObjectList,getActivity(),this);
        rv_world_recipe.setAdapter(wra);
    }

    @Override
    public void viewItem(String img, String name, String type, String price, String place, String mainIngredient, String ingredient, String tools, String step) {
        //ViewRecipe viewRecipe = new ViewRecipe();
        Bundle bundle = new Bundle();
        bundle.putString("image",img);
        bundle.putString("name",name);
        bundle.putString("type",type);
        bundle.putString("price",price);
        bundle.putString("place",place);
        bundle.putString("mainIngredient",mainIngredient);
        bundle.putString("ingredient",ingredient);
        bundle.putString("tools", tools);
        bundle.putString("step", step);
        //viewRecipe.setArguments(bundle);
        Log.d("data", "image : " + img);
        Log.d("data", "name : " + name);
        Log.d("data", "type : "+type);
        Log.d("data", "price : "+price);
        Log.d("data", "place : "+place);
        Log.d("data", "mainigredient : "+mainIngredient);
        Log.d("data", "igredient : "+ingredient);
        Log.d("data", "tools : "+tools);
        Log.d("data", "step : "+step);
        Intent intent = new Intent(getActivity(),ViewRecipe.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
