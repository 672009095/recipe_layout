package com.skyshi.training_recipe_beginers;

/**
 * Created by skyshi on 22/04/16.
 */
public interface ItemClickListener {
    public void viewItem(int id,String img,String name,String type,String price,String place,
                         String mainIngredient,String ingredient,String tools,String step);
}
