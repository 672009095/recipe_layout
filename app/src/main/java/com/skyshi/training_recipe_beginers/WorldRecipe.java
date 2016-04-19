package com.skyshi.training_recipe_beginers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyshi.training_recipe_beginers.World.WorldObject;
import com.skyshi.training_recipe_beginers.World.WorldRecipeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyshi on 18/04/16.
 */
public class WorldRecipe extends Fragment {
    RecyclerView rv_world_recipe;
    LinearLayoutManager llm_manager;
    WorldRecipeAdapter wra;
    List<WorldObject> worldObjectList= new ArrayList<>();
    public WorldRecipe(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_worl_recipe,container,false);
        rv_world_recipe = (RecyclerView)root.findViewById(R.id.rv_world_recipe);
        rv_world_recipe.setHasFixedSize(true);

        llm_manager = new LinearLayoutManager(getActivity().getBaseContext());
        rv_world_recipe.setLayoutManager(llm_manager);
        for (int i = 0; i <20 ; i++) {
            worldObjectList.add(new WorldObject(1, "bulu babi bakar", "fast food", "bulu babi", "wajan", "dibakar", "10000", "japan"));
        }
        //worldObjectList.add(new WorldObject(2, "babi bakar", "fast food", "bulu babi", "wajan", "dibakar", "10000", "japan"));
        //worldObjectList.add(new WorldObject(3, "sapi panggang", "fast food", "bulu babi", "wajan", "digoreng", "10000", "china"));
        wra = new WorldRecipeAdapter(worldObjectList);
        rv_world_recipe.setAdapter(wra);
        return root;
    }
}
