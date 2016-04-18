package com.skyshi.training_recipe_beginers.World;

/**
 * Created by skyshi on 18/04/16.
 */
public class WorldObject {
    private int id;
    private String name_food;
    private String type;
    private String ingredients;
    private String tools;
    private String step;
    private String price;
    private String original_place;
    public WorldObject(int id,String name_food,String type,String ingredients,
                       String tools,String step,String price,String original_place){
        this.setId(id);
        this.setName_food(name_food);
        this.setType(type);
        this.setIngredients(ingredients);
        this.setTools(tools);
        this.setStep(step);
        this.setPrice(price);
        this.setOriginal_place(original_place);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_food() {
        return name_food;
    }

    public void setName_food(String name_food) {
        this.name_food = name_food;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginal_place() {
        return original_place;
    }

    public void setOriginal_place(String original_place) {
        this.original_place = original_place;
    }
}
