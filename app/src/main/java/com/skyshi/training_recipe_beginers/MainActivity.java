package com.skyshi.training_recipe_beginers;

import android.content.res.Configuration;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DrawerLayout mDrawerLayout;
    RelativeLayout rel_menuleft,rel_main;
    ActionBarDrawerToggle mDrawerToogle;
    TabLayout tabLayout ;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");

        mDrawerLayout = (DrawerLayout)findViewById(R.id.mDrawer);
        rel_main = (RelativeLayout)findViewById(R.id.rel_mainCenter);
        rel_menuleft = (RelativeLayout)findViewById(R.id.rel_menuLeft);

        setNavigationBar();

        viewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout)findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
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
        adapter.addFragment(new LocalRecipe(),"Local");
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter{
        private final List<Fragment>mFragmentList = new ArrayList<>();
        private final List<String>mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
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
