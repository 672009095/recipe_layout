package com.skyshi.training_recipe_beginers.Database;

import com.skyshi.training_recipe_beginers.Local.LocalObject;
import com.skyshi.training_recipe_beginers.World.WorldObject;

import java.util.List;

/**
 * Created by skyshi on 19/04/16.
 */
public interface CRUDINTERFACE {
    //add
    public void addWorldRecipe(WorldObject worldObject);
    public void addLocalRecipe(LocalObject localObject);
    //get where id
    public WorldObject getWorldRecipe(int id);
    public LocalObject getLocalRecipe(int id);
    //get all
    public List<WorldObject> getAllWorldRecipe();
    public List<LocalObject> getAllLocalRecipe();
    //update
    public int updateWorldRecipe(WorldObject worldObject);
    public int updateLocaldRecipe(LocalObject localObject);
    //delete
    public void deleteWorldRecipe(WorldObject worldObject);
    public void deleteLocaldRecipe(LocalObject localObject);
    //count table
    public int getWorldRecipeCount();
    public int getLocaldRecipeCount();
}
