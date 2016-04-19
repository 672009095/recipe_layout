package com.skyshi.training_recipe_beginers.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.skyshi.training_recipe_beginers.Local.LocalObject;
import com.skyshi.training_recipe_beginers.World.WorldObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by skyshi on 19/04/16.
 */
public class DatabaseHandler extends SQLiteOpenHelper implements CRUDINTERFACE{
    private static final String DATABASE_NAME = "recipedb";
    private static final int DATABASE_VERSION = 1;
    //name table
    private static final String WORLD_TABLE = "worldtable";
    private static final String LOCAL_TABLE = "localtable";
    //name column
    private static final String KEY_ID = "id";
    private static final String KEY_NAMEFOOD = "name_food";
    private static final String KEY_TYPE = "type";
    private static final String KEY_MAININGREDIENTS = "main_ingredients";
    private static final String KEY_INGREDIENTS = "ingredients";
    private static final String KEY_TOOLS = "tools";
    private static final String KEY_STEP = "step";
    private static final String KEY_PRICE = "price";
    private static final String KEY_ORIGINALPLACE = "original_place";
    private static final String KEY_IMAGENAME = "image_name";
    //query create table
    private static final String CREATE_WORLD_TABLE =
            "CREATE TABLE "+ WORLD_TABLE + "("
            + KEY_ID + " INTEGER PRIMARY KEY,"
            + KEY_NAMEFOOD +" TEXT,"
            + KEY_TYPE +" TEXT,"
            + KEY_MAININGREDIENTS +" TEXT,"
            + KEY_INGREDIENTS +" TEXT,"
            + KEY_TOOLS +" TEXT,"
            + KEY_STEP +" TEXT,"
            + KEY_PRICE +" TEXT,"
            + KEY_ORIGINALPLACE +" TEXT,"
            + KEY_IMAGENAME +" TEXT"
            +")";
    private static final String CREATE_LOCAL_TABLE =
            "CREATE TABLE "+ LOCAL_TABLE + "("
                    + KEY_ID + " INTEGER PRIMARY KEY,"
                    + KEY_NAMEFOOD +" TEXT,"
                    + KEY_TYPE +" TEXT,"
                    + KEY_MAININGREDIENTS +" TEXT,"
                    + KEY_INGREDIENTS +" TEXT,"
                    + KEY_TOOLS +" TEXT,"
                    + KEY_STEP +" TEXT,"
                    + KEY_PRICE +" TEXT,"
                    + KEY_ORIGINALPLACE +" TEXT,"
                    + KEY_IMAGENAME +" TEXT"
                    +")";

    public DatabaseHandler(Context context){
        super (context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_LOCAL_TABLE);
        sqLiteDatabase.execSQL(CREATE_WORLD_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + LOCAL_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST " + WORLD_TABLE);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void addWorldRecipe(WorldObject worldObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAMEFOOD,worldObject.getName_food());
        cv.put(KEY_TYPE,worldObject.getType());
        cv.put(KEY_MAININGREDIENTS,worldObject.getMain_ingredients());
        cv.put(KEY_INGREDIENTS,worldObject.getIngredients());
        cv.put(KEY_TOOLS,worldObject.getTools());
        cv.put(KEY_STEP,worldObject.getStep());
        cv.put(KEY_PRICE,worldObject.getPrice());
        cv.put(KEY_ORIGINALPLACE,worldObject.getOriginal_place());
        cv.put(KEY_IMAGENAME,worldObject.getImage_name());

        db.insert(WORLD_TABLE, null, cv);
        db.close();
    }

    @Override
    public void addLocalRecipe(LocalObject localObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAMEFOOD,localObject.getName_food());
        cv.put(KEY_TYPE,localObject.getType());
        cv.put(KEY_MAININGREDIENTS,localObject.getMain_ingredients());
        cv.put(KEY_INGREDIENTS,localObject.getIngredients());
        cv.put(KEY_TOOLS,localObject.getTools());
        cv.put(KEY_STEP,localObject.getStep());
        cv.put(KEY_PRICE,localObject.getPrice());
        cv.put(KEY_ORIGINALPLACE,localObject.getOriginal_place());
        cv.put(KEY_IMAGENAME,localObject.getImage_name());

        db.insert(LOCAL_TABLE,null,cv);
        db.close();
    }

    @Override
    public WorldObject getWorldRecipe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(WORLD_TABLE,new String[]{
                KEY_ID,KEY_NAMEFOOD,KEY_TYPE,KEY_MAININGREDIENTS,KEY_INGREDIENTS,
                KEY_TOOLS,KEY_STEP,KEY_PRICE,KEY_ORIGINALPLACE,KEY_IMAGENAME
        },KEY_ID+ "=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        WorldObject wo = new WorldObject(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9)
        );
        return wo;
    }

    @Override
    public LocalObject getLocalRecipe(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(LOCAL_TABLE,new String[]{
                KEY_ID,KEY_NAMEFOOD,KEY_TYPE,KEY_MAININGREDIENTS,KEY_INGREDIENTS,
                KEY_TOOLS,KEY_STEP,KEY_PRICE,KEY_ORIGINALPLACE,KEY_IMAGENAME
        },KEY_ID+ "=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor !=null)
            cursor.moveToFirst();
        LocalObject lo = new LocalObject(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5),
                cursor.getString(6),
                cursor.getString(7),
                cursor.getString(8),
                cursor.getString(9)
        );
        return lo;
    }

    @Override
    public List<WorldObject> getAllWorldRecipe() {
        List<WorldObject>worldObjectList = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+WORLD_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                WorldObject worldObject = new WorldObject();
                worldObject.setId(Integer.parseInt(cursor.getString(0)));
                worldObject.setName_food(cursor.getString(1));
                worldObject.setType(cursor.getString(2));
                worldObject.setMain_ingredients(cursor.getString(3));
                worldObject.setIngredients(cursor.getString(4));
                worldObject.setTools(cursor.getString(5));
                worldObject.setStep(cursor.getString(6));
                worldObject.setPrice(cursor.getString(7));
                worldObject.setOriginal_place(cursor.getString(8));
                worldObject.setImage_name(cursor.getString(9));
                //add object to list
                worldObjectList.add(worldObject);
            }while(cursor.moveToNext());
        }
        return worldObjectList;
    }

    @Override
    public List<LocalObject> getAllLocalRecipe() {
        List<LocalObject>localObjectList = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+LOCAL_TABLE;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.moveToFirst()){
            do{
                LocalObject localObject = new LocalObject();
                localObject.setId(Integer.parseInt(cursor.getString(0)));
                localObject.setName_food(cursor.getString(1));
                localObject.setType(cursor.getString(2));
                localObject.setMain_ingredients(cursor.getString(3));
                localObject.setIngredients(cursor.getString(4));
                localObject.setTools(cursor.getString(5));
                localObject.setStep(cursor.getString(6));
                localObject.setPrice(cursor.getString(7));
                localObject.setOriginal_place(cursor.getString(8));
                localObject.setImage_name(cursor.getString(9));
                //add object to list
                localObjectList.add(localObject);
            }while(cursor.moveToNext());
        }
        return localObjectList;
    }

    @Override
    public int updateWorldRecipe(WorldObject worldObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAMEFOOD,worldObject.getName_food());
        cv.put(KEY_TYPE,worldObject.getType());
        cv.put(KEY_MAININGREDIENTS,worldObject.getMain_ingredients());
        cv.put(KEY_INGREDIENTS,worldObject.getIngredients());
        cv.put(KEY_TOOLS,worldObject.getTools());
        cv.put(KEY_STEP,worldObject.getStep());
        cv.put(KEY_PRICE,worldObject.getPrice());
        cv.put(KEY_ORIGINALPLACE,worldObject.getOriginal_place());
        cv.put(KEY_IMAGENAME,worldObject.getImage_name());

        return db.update(WORLD_TABLE,cv,KEY_ID + " = ?",
                new String[]{String.valueOf(worldObject.getId())});
    }

    @Override
    public int updateLocaldRecipe(LocalObject localObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAMEFOOD,localObject.getName_food());
        cv.put(KEY_TYPE, localObject.getType());
        cv.put(KEY_MAININGREDIENTS,localObject.getMain_ingredients());
        cv.put(KEY_INGREDIENTS,localObject.getIngredients());
        cv.put(KEY_TOOLS,localObject.getTools());
        cv.put(KEY_STEP,localObject.getStep());
        cv.put(KEY_PRICE,localObject.getPrice());
        cv.put(KEY_ORIGINALPLACE,localObject.getOriginal_place());
        cv.put(KEY_IMAGENAME,localObject.getImage_name());

        return db.update(LOCAL_TABLE,cv,KEY_ID + " = ?",
                new String[]{String.valueOf(localObject.getId())});
    }

    @Override
    public void deleteWorldRecipe(WorldObject worldObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(WORLD_TABLE,KEY_ID +" = ?",
                new String[]{String.valueOf(worldObject.getId())});
        db.close();
    }

    @Override
    public void deleteLocaldRecipe(LocalObject localObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(LOCAL_TABLE,KEY_ID +" = ?",
                new String[]{String.valueOf(localObject.getId())});
        db.close();
    }

    @Override
    public int getWorldRecipeCount() {
        String countQuery = "SELECT * FROM "+WORLD_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }

    @Override
    public int getLocaldRecipeCount() {
        String countQuery = "SELECT * FROM "+LOCAL_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }
}
