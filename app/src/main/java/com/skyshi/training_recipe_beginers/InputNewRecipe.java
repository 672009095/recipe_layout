package com.skyshi.training_recipe_beginers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
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

import com.bumptech.glide.Glide;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
    private static final int CODE_ADD = 90;
    List<String> listMainIngredient = new ArrayList<>();
    List<String> listIngredients = new ArrayList<>();
    List<String> listTools = new ArrayList<>();
    String category = "",typeRecipe = "",appetizer = "",maincourse = "",dessert = "";

    private Uri fileUri;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int MEDIA_TYPE_IMAGE = 1;
    private static final String IMAGE_DIRECTORY_NAME = "RecipeCamera";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);
        nameRecipe = (EditText)findViewById(R.id.edit_name_recipe);
        editText_mainIngredients = (EditText)findViewById(R.id.edit_mainIngredients);
        editText_mainIngredients.setEnabled(false);
        editText_ingredients = (EditText)findViewById(R.id.edit_ingredients);
        editText_ingredients.setEnabled(false);
        editText_tools = (EditText)findViewById(R.id.edit_tools);
        editText_tools.setEnabled(false);
        editText_step = (EditText)findViewById(R.id.edit_step);
        editText_price = (EditText)findViewById(R.id.edit_price);
        editText_place = (EditText)findViewById(R.id.edit_place);
        img_recipe_photo = (ImageView)findViewById(R.id.img_recipe_photo);
        img_recipe_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });

        radioGroup = (RadioGroup)findViewById(R.id.radio_group);

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
                typeRecipe = appetizer+" "+maincourse+" "+dessert;
                if(typeRecipe.substring(typeRecipe.length()-1).equalsIgnoreCase(" ")) {
                    typeRecipe = typeRecipe.substring(0, typeRecipe.length() - 1);
                }
                int selectedCategory = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(selectedCategory);
                if(radioButton.getText().toString().equalsIgnoreCase("World")){
                    category = radioButton.getText().toString();
                }else{
                    category = radioButton.getText().toString();
                }
                Intent intent= new Intent();
                intent.putExtra("namerecipe", nameRecipe.getText().toString());
                intent.putExtra("category", category);
                intent.putExtra("type", typeRecipe);
                intent.putExtra("mainingredient", editText_mainIngredients.getText().toString().trim());
                intent.putExtra("ingredient", editText_ingredients.getText().toString().trim());
                intent.putExtra("tools", editText_tools.getText().toString().trim());
                intent.putExtra("step", editText_step.getText().toString());
                intent.putExtra("price", editText_price.getText().toString());
                intent.putExtra("place", editText_place.getText().toString());
                intent.putExtra("imagepath",fileUri.toString());
                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("file_uri",fileUri);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        fileUri = savedInstanceState.getParcelable("file_uri");
    }

    public void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }
    public Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }
    private static File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),IMAGE_DIRECTORY_NAME);
        if(!mediaStorageDir.exists()){
            if(!mediaStorageDir.mkdirs()){
                return null;
            }
        }
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        File mediaFile;
        if(type==MEDIA_TYPE_IMAGE){
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        }else{
            return null;
        }
        return mediaFile;
    }
    private void previewCapturedImage(){
        try {
            /*BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 4;
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),options);
            img_recipe_photo.setImageBitmap(bitmap);*/
            Glide.with(this)
                    .load(fileUri.getPath())
                    .fitCenter()
                    .placeholder(R.drawable.noimage)
                    .into(img_recipe_photo);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAMERA_CAPTURE_IMAGE_REQUEST_CODE){
            if(resultCode ==Activity.RESULT_OK){
                previewCapturedImage();
            }else if(resultCode ==RESULT_CANCELED){
                return;
            }else{
                return;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
