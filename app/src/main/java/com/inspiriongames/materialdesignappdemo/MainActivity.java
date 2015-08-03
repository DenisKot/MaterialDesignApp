package com.inspiriongames.materialdesignappdemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String SELECTED_ITEM_ID = "selected_id";
    private static final String FIRST_TIME = "first_time";

    private Toolbar toolbar;
    private NavigationView mDrawer;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private int mSelectedId;
    private boolean mUserSawDrawer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawer = (NavigationView) findViewById(R.id.mainDrawer);
        mDrawer.setNavigationItemSelectedListener(this);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_closed);
        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();

        if(!didUserSawDrawer()){
            showDrawer();
            markDrawerSeen();
        } else {
            hideDrawer();
        }

        mSelectedId = savedInstanceState == null ? R.id.nav_item_1 : savedInstanceState.getInt(SELECTED_ITEM_ID);
        navigate();

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_layout);
        collapsingToolbarLayout.setTitle(getString(R.string.my_name));
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(android.R.color.white));
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(android.R.color.white));
        //final ImageView imageView = (ImageView) findViewById(R.id.meImageView);
        //Glide.with(this).load(R.drawable.me).centerCrop().into(imageView);
    }

    private void navigate() {

    }

    private boolean didUserSawDrawer(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserSawDrawer = sharedPreferences.getBoolean(FIRST_TIME, false);
        return mUserSawDrawer;
    }

    private void markDrawerSeen(){
        mUserSawDrawer = true;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.edit().putBoolean(FIRST_TIME, mUserSawDrawer).apply();
    }

    private void showDrawer(){
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void hideDrawer(){
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem menuItem) {
        menuItem.setChecked(true);
        mSelectedId = menuItem.getItemId();

        Intent intent = null;

        switch (menuItem.getItemId()){
            case R.id.nav_item_1:
                drawerLayout.closeDrawer(GravityCompat.START);
                intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_item_2:
                drawerLayout.closeDrawer(GravityCompat.START);
                intent = new Intent(this, TabsActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_item_3:
                drawerLayout.closeDrawer(GravityCompat.START);
                intent = new Intent(this, MaterialActivity.class);
                startActivity(intent);
                break;

            case R.id.nav_item_4:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            case R.id.nav_item_5:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

            default: return false;
        }

        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(SELECTED_ITEM_ID, mSelectedId);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
