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
import com.skyshi.training_recipe_beginers.Local.LocalObject;
import com.skyshi.training_recipe_beginers.Local.LocalRecipeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyshi on 18/04/16.
 */
public class LocalRecipe extends Fragment implements ItemClickListener{
    RecyclerView rv_local_recipe;
    LinearLayoutManager llm_manager;
    LocalRecipeAdapter lra;
    List<LocalObject>localObjectList = new ArrayList<>();
    DatabaseHandler dbHandler;
    public LocalRecipe(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_local_recipe,container,false);
        dbHandler = new DatabaseHandler(getActivity().getBaseContext());
        rv_local_recipe = (RecyclerView)view.findViewById(R.id.rv_local_recipe);
        rv_local_recipe.setHasFixedSize(true);

        llm_manager = new LinearLayoutManager(getActivity().getBaseContext());
        rv_local_recipe.setLayoutManager(llm_manager);
        /*if(localObjectList.size()==0) {
            for (int i = 0; i < 20; i++) {
                localObjectList.add(new LocalObject(3, "sapi panggang", "fast food", "bulu babi", "bawang", "wajan", "digoreng", "10000", "china", ""));
            }
        }*/
        localObjectList = dbHandler.getAllLocalRecipe();
        lra = new LocalRecipeAdapter(localObjectList,getActivity(),this);
        rv_local_recipe.setAdapter(lra);
        return view;
    }
    public void refreshListLocal(){
        localObjectList.clear();
        localObjectList = dbHandler.getAllLocalRecipe();
        lra = new LocalRecipeAdapter(localObjectList,getActivity(),this);
        rv_local_recipe.setAdapter(lra);
    }

    @Override
    public void viewItem(String img, String name, String type, String price, String place, String mainIngredient, String ingredient, String tools, String step) {
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
