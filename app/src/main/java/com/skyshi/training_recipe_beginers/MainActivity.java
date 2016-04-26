package com.skyshi.training_recipe_beginers;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.skyshi.training_recipe_beginers.Database.DatabaseHandler;
import com.skyshi.training_recipe_beginers.Local.LocalObject;
import com.skyshi.training_recipe_beginers.World.WorldObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    RelativeLayout rel_menuleft,rel_main;
    ActionBarDrawerToggle mDrawerToogle;
    TabLayout tabLayout ;
    ViewPager viewPager;
    private static final int CODE_ADD = 90;
    private int[] tabIcons = {
            R.drawable.world,R.drawable.local
    };
    LocalRecipe localRecipeFragment;
    WorldRecipe worldRecipeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent create = new Intent(MainActivity.this,InputNewRecipe.class);
                    startActivityForResult(create,CODE_ADD);
                }
            });
        }

        mDrawerLayout = (DrawerLayout)findViewById(R.id.mDrawer);
        rel_main = (RelativeLayout)findViewById(R.id.rel_mainCenter);
        rel_menuleft = (RelativeLayout)findViewById(R.id.rel_menuLeft);

        setNavigationBar();

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(mDrawerToogle.onOptionsItemSelected(item)){
            if(mDrawerLayout.isDrawerVisible(rel_menuleft)){
                mDrawerLayout.closeDrawer(rel_menuleft);
            }else{
                mDrawerLayout.openDrawer(rel_menuleft);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToogle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToogle.syncState();
    }

    public void setNavigationBar(){
        mDrawerToogle = new ActionBarDrawerToggle(this,mDrawerLayout,R.string.app_name,R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView) {
                //super.onDrawerClosed(drawerView);
                mDrawerLayout.closeDrawer(rel_menuleft);
                supportInvalidateOptionsMenu();
                mDrawerToogle.syncState();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                //super.onDrawerOpened(drawerView);
                mDrawerLayout.openDrawer(rel_menuleft);
                supportInvalidateOptionsMenu();
                mDrawerToogle.syncState();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(rel_menuleft, slideOffset);
                mDrawerLayout.setTranslationX(slideOffset * rel_menuleft.getWidth());
                mDrawerLayout.bringChildToFront(rel_menuleft);
                mDrawerLayout.requestLayout();
            }
        };
        mDrawerToogle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mDrawerToogle);
    }
    private void setupViewPager(ViewPager viewPager){
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new WorldRecipe(),"World");
        adapter.addFragment(new LocalRecipe(), "Local");
        viewPager.setAdapter(adapter);
    }
    private void setupTabIcons(){
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_ADD){
            if(resultCode == Activity.RESULT_OK){
                DatabaseHandler db = new DatabaseHandler(this);
                if(data.getStringExtra("category").equalsIgnoreCase("world")) {
                    db.addWorldRecipe(new WorldObject(
                        0,
                        data.getStringExtra("namerecipe"),
                        data.getStringExtra("type"),
                        data.getStringExtra("mainingredient"),
                        data.getStringExtra("ingredient"),
                        data.getStringExtra("tools"),
                        data.getStringExtra("step"),
                        data.getStringExtra("price"),
                        data.getStringExtra("place"),
                        data.getStringExtra("imagepath")
                    ));
                    worldRecipeFragment.refreshListWorld();
                }else{
                    db.addLocalRecipe(new LocalObject(
                        0,
                        data.getStringExtra("namerecipe"),
                        data.getStringExtra("type"),
                        data.getStringExtra("mainingredient"),
                        data.getStringExtra("ingredient"),
                        data.getStringExtra("tools"),
                        data.getStringExtra("step"),
                        data.getStringExtra("price"),
                        data.getStringExtra("place"),
                        data.getStringExtra("imagepath")
                    ));
                    localRecipeFragment.refreshListLocal();
                }
            }else{
                return;
            }
        }
    }
    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment>mFragmentList = new ArrayList<>();
        private final List<String>mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    worldRecipeFragment = new WorldRecipe();
                    return worldRecipeFragment;
                case 1:
                    localRecipeFragment = new LocalRecipe();
                    return localRecipeFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }
        public void addFragment(Fragment fragment,String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
