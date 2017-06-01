package com.example.raoyinchen.gridviewtest;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}




class ImageAdapter extends BaseAdapter {
    private Context mContext;

    ArrayList<String> urls = new ArrayList<String>();


    public ImageAdapter(Context c) {
        mContext = c;
        //this.mThumdIds = imageView;
        urls.add("http://ec2-52-10-214-108.us-west-2.compute.amazonaws.com:80/parse/files/f92b7a419b84dc890cbce723cbdf89e5f58e8db9/8e4124b5648417b4d128f0ff2d2a9b2d_file");
        urls.add("http://ec2-52-10-214-108.us-west-2.compute.amazonaws.com:80/parse/files/f92b7a419b84dc890cbce723cbdf89e5f58e8db9/8e4124b5648417b4d128f0ff2d2a9b2d_file");
        urls.add("http://ec2-52-10-214-108.us-west-2.compute.amazonaws.com:80/parse/files/f92b7a419b84dc890cbce723cbdf89e5f58e8db9/8e4124b5648417b4d128f0ff2d2a9b2d_file");
        urls.add("http://ec2-52-10-214-108.us-west-2.compute.amazonaws.com:80/parse/files/f92b7a419b84dc890cbce723cbdf89e5f58e8db9/8e4124b5648417b4d128f0ff2d2a9b2d_file");
        urls.add("http://ec2-52-10-214-108.us-west-2.compute.amazonaws.com:80/parse/files/f92b7a419b84dc890cbce723cbdf89e5f58e8db9/8e4124b5648417b4d128f0ff2d2a9b2d_file");
        urls.add("http://ec2-52-10-214-108.us-west-2.compute.amazonaws.com:80/parse/files/f92b7a419b84dc890cbce723cbdf89e5f58e8db9/8e4124b5648417b4d128f0ff2d2a9b2d_file");
        urls.add("http://ec2-52-10-214-108.us-west-2.compute.amazonaws.com:80/parse/files/f92b7a419b84dc890cbce723cbdf89e5f58e8db9/8e4124b5648417b4d128f0ff2d2a9b2d_file");
        urls.add("http://ec2-52-10-214-108.us-west-2.compute.amazonaws.com:80/parse/files/f92b7a419b84dc890cbce723cbdf89e5f58e8db9/8e4124b5648417b4d128f0ff2d2a9b2d_file");
        urls.add("http://ec2-52-10-214-108.us-west-2.compute.amazonaws.com:80/parse/files/f92b7a419b84dc890cbce723cbdf89e5f58e8db9/8e4124b5648417b4d128f0ff2d2a9b2d_file");
        urls.add("http://ec2-52-10-214-108.us-west-2.compute.amazonaws.com:80/parse/files/f92b7a419b84dc890cbce723cbdf89e5f58e8db9/8e4124b5648417b4d128f0ff2d2a9b2d_file");
    }

    public int getCount() {
        return this.urls.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(12,12,12,12);
        } else {
            imageView = (ImageView) convertView;
        }
        Picasso.with(mContext)
                .load(urls.get(position))
                .resize(200,200)
                .into(imageView);
        //imageView.setImageDrawable(mThumdIds.get(position).getDrawable());
        return imageView;
    }
}





