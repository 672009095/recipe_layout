package com.skyshi.training_recipe_beginers.Local;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skyshi.training_recipe_beginers.Database.DatabaseHandler;
import com.skyshi.training_recipe_beginers.Local.LocalObject;
import com.skyshi.training_recipe_beginers.Local.LocalRecipeAdapter;
import com.skyshi.training_recipe_beginers.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyshi on 18/04/16.
 */
public class LocalRecipe extends Fragment {
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
        lra = new LocalRecipeAdapter(localObjectList,getActivity());
        rv_local_recipe.setAdapter(lra);
        return view;
    }
    public void refreshListLocal(){
        localObjectList.clear();
        localObjectList = dbHandler.getAllLocalRecipe();
        lra = new LocalRecipeAdapter(localObjectList,getActivity());
        rv_local_recipe.setAdapter(lra);
    }
}