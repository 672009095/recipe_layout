package com.skyshi.training_recipe_beginers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyshi on 19/04/16.
 */
public class InputNewRecipe extends AppCompatActivity{
    EditText nameRecipe,editText_mainIngredients,editText_ingredients
            ,editText_tools,editText_step,editText_price,editText_place;
    RadioGroup radioGroup;
    ImageView img_recipe_photo;
    RadioButton radioButton;
    CheckBox checkBoxAppetizer,checkBoxMaincourse,checkBoxDessert;
    Spinner spinner_mainIngredients,spinner_ingredients,spinner_tools;
    ArrayAdapter<CharSequence> adapterMainIngredient,adapterIngredient,adapterTools;
    FloatingActionButton fab_input_recipe;
    List<String> listMainIngredient = new ArrayList<>();
    List<String> listIngredients = new ArrayList<>();
    List<String> listTools = new ArrayList<>();
    String category = "",typeRecipe = "",appetizer = "",maincourse = "",dessert = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        nameRecipe = (EditText)findViewById(R.id.edit_name_recipe);
        editText_mainIngredients = (EditText)findViewById(R.id.edit_mainIngredients);
        editText_ingredients = (EditText)findViewById(R.id.edit_ingredients);
        editText_tools = (EditText)findViewById(R.id.edit_tools);
        editText_step = (EditText)findViewById(R.id.edit_step);
        editText_price = (EditText)findViewById(R.id.edit_price);
        editText_place = (EditText)findViewById(R.id.edit_place);
        img_recipe_photo = (ImageView)findViewById(R.id.img_recipe_photo);

        radioGroup = (RadioGroup)findViewById(R.id.radio_group);
        int selectedCategory = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton)findViewById(selectedCategory);
        if(radioButton.getText().toString().equalsIgnoreCase("World")){
            category = radioButton.getText().toString();
        }else{
            category = radioButton.getText().toString();
        }

        checkBoxAppetizer = (CheckBox)findViewById(R.id.check_appetizer);
        checkBoxAppetizer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBoxAppetizer.isChecked()){
                    appetizer = "Appetizer";
                }else{
                    appetizer = "";
                }
            }
        });

        checkBoxMaincourse = (CheckBox)findViewById(R.id.check_maincourse);
        checkBoxMaincourse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBoxMaincourse.isChecked()){
                    maincourse = "Main Course";
                }else{
                    maincourse = "";
                }
            }
        });
        checkBoxDessert = (CheckBox)findViewById(R.id.check_dessert);
        checkBoxDessert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBoxDessert.isChecked()){
                    dessert = "Dessert";
                }else{
                    dessert = "";
                }
            }
        });

        spinner_mainIngredients = (Spinner)findViewById(R.id.spinner_main_ingredients);
        adapterMainIngredient = ArrayAdapter.createFromResource(this,R.array.array_main_ingredients,android.R.layout.simple_spinner_item);
        adapterMainIngredient.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_mainIngredients.setAdapter(adapterMainIngredient);
        spinner_mainIngredients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!adapterView.getItemAtPosition(i).toString().contains("Select")) {
                    editText_mainIngredients.setText(editText_mainIngredients.getText() + adapterView.getItemAtPosition(i).toString() + ",");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_ingredients = (Spinner)findViewById(R.id.spinner_ingredients);
        adapterIngredient = ArrayAdapter.createFromResource(this,R.array.array_ingredients,android.R.layout.simple_spinner_item);
        adapterIngredient.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_ingredients.setAdapter(adapterIngredient);
        spinner_ingredients.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!adapterView.getItemAtPosition(i).toString().contains("Select")) {
                    editText_ingredients.setText(editText_ingredients.getText() + adapterView.getItemAtPosition(i).toString() + ",");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner_tools = (Spinner)findViewById(R.id.spinner_tools);
        adapterTools = ArrayAdapter.createFromResource(this,R.array.array_tools,android.R.layout.simple_spinner_item);
        adapterTools.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_tools.setAdapter(adapterTools);
        spinner_tools.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!adapterView.getItemAtPosition(i).toString().contains("Select")) {
                    editText_tools.setText(editText_tools.getText() + adapterView.getItemAtPosition(i).toString() + ",");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fab_input_recipe = (FloatingActionButton)findViewById(R.id.fab_submit_recipe);
        fab_input_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                typeRecipe = appetizer+","+maincourse+","+dessert;
                if(typeRecipe.substring(typeRecipe.length()-1).equalsIgnoreCase(",")) {
                    typeRecipe = typeRecipe.substring(0, typeRecipe.length() - 1);
                }
                Log.d("tesInput","name recipe : "+nameRecipe.getText().toString());
                Log.d("tesInput","name category : "+category);
                Log.d("tesInput","name type : "+typeRecipe);
                Log.d("tesInput","main ingredient : "+editText_mainIngredients.getText().toString());
                Log.d("tesInput","ingredient :"+editText_ingredients.getText().toString());
                Log.d("tesInput","tools : "+editText_tools.getText().toString());
                Log.d("tesInput","step : "+editText_step.getText().toString());
                Log.d("tesInput","price : "+editText_price.getText().toString());
                Log.d("tesInput","plcae : "+editText_place.getText().toString());
            }
        });
    }

}
