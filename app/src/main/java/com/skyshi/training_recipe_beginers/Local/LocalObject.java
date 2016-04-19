package com.skyshi.training_recipe_beginers.Local;

/**
 * Created by skyshi on 19/04/16.
 */
public class LocalObject {
    private int id;
    private String name_food;
    private String type;
    private String main_ingredients;
    private String ingredients;
    private String tools;
    private String step;
    private String price;
    private String original_place;
    private String image_name;
    public LocalObject(){}
    public LocalObject(int id,String name_food,String type,String main_ingredients,String ingredients,
                       String tools,String step,String price,String original_place,
                       String image_name){
        this.setId(id);
        this.setName_food(name_food);
        this.setType(type);
        this.setMain_ingredients(main_ingredients);
        this.setIngredients(ingredients);
        this.setTools(tools);
        this.setStep(step);
        this.setPrice(price);
        this.setOriginal_place(original_place);
        this.setImage_name(image_name);
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

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }

    public String getMain_ingredients() {
        return main_ingredients;
    }

    public void setMain_ingredients(String main_ingredients) {
        this.main_ingredients = main_ingredients;
    }
}
