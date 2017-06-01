package com.parse.starter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class myprofileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

   public static ArrayList<String> imageArray;
    public ImageAdapter adapter;
    public void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//importante!!
        startActivityForResult(intent,1);
        imageUpdate();
    }
    public void changeProfile() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//importante!!
        startActivityForResult(intent,2);
    }

    public void imageUpdate() {
        imageArray.clear();
        final ParseQuery<ParseObject> imagequery = new ParseQuery<ParseObject>("Image");
        imagequery.whereEqualTo("username",ParseUser.getCurrentUser().getUsername());
        imagequery.addDescendingOrder("createdAt");

        imagequery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    if(objects.size() > 0) {
                        Log.i("number of image",String.valueOf(objects.size()));
                        for(ParseObject object:objects) {
                            ParseFile file = (ParseFile) object.get("image");
                            imageArray.add(file.getUrl().toString());
                            Log.i("url address", file.getUrl());
                            Log.i("url size", String.valueOf(imageArray.size()));
//                            file.getDataInBackground(new GetDataCallback() {
//                                @Override
//                                public void done(byte[] data, ParseException e) {
//                                    if(e == null && data != null) {
//                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
//                                        Bitmap.createScaledBitmap(bitmap, 150, 150, false);
//                                        ImageView image = new ImageView(getApplicationContext());
//                                        image.setLayoutParams(new ViewGroup.LayoutParams(
//                                                700,
//                                                ViewGroup.LayoutParams.WRAP_CONTENT
//                                        ));
//                                        image.setImageBitmap(bitmap);
//
//                                        Log.i("bitmap adding here",String.valueOf(imageArray.size()));
//
//                                    }
//                                }
//                            });

                        }
                    }
                }
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
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
//        MyAsyncTask thetask = new MyAsyncTask();
//        thetask.execute(null,null,null);
//        thetask.onPostExecute(null);
       //imageArray.clear();

        final GridView gridView = (GridView) findViewById(R.id.myGridView);
        imageArray = new ArrayList<>();
        final ParseQuery<ParseObject> imagequery = new ParseQuery<ParseObject>("Image");
        imagequery.whereEqualTo("username",ParseUser.getCurrentUser().getUsername());
        imagequery.addDescendingOrder("createdAt");

        imagequery.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    if(objects.size() > 0) {
                        Log.i("number of image",String.valueOf(objects.size()));
                        for(ParseObject object:objects) {
                            ParseFile file = (ParseFile) object.get("image");
                            imageArray.add(file.getUrl().toString());
                        }
                        ArrayList<String>urls = new ArrayList<>(imageArray);
                        // GridView gridView = (GridView) findViewById(R.id.myGridView);
                        Log.i("oncreat called","urls size is" + String.valueOf(urls.size()));
                        adapter = new ImageAdapter(getApplicationContext(), urls);
                        gridView.setAdapter(adapter);

                        //imageUpdate();
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });


        ArrayList<String> urls = new ArrayList<>(imageArray);
       // GridView gridView = (GridView) findViewById(R.id.myGridView);
        Log.i("oncreat called","urls size is" + String.valueOf(urls.size()));
        adapter = new ImageAdapter(this,urls);
        gridView.setAdapter(adapter);

        //imageUpdate();
        adapter.notifyDataSetChanged();


        //gridView.notify();
//        ParseQuery<ParseObject> imagequery = new ParseQuery<ParseObject>("Image");
//        imagequery.whereEqualTo("username",ParseUser.getCurrentUser().getUsername());
//        imagequery.addDescendingOrder("createdAt");



//        imagequery.findInBackground(new FindCallback<ParseObject>() {
//            @Override
//            public void done(List<ParseObject> objects, ParseException e) {
//                if(e == null) {
//                    if(objects.size() > 0) {
//                        Log.i("number of image",String.valueOf(objects.size()));
//                        for(ParseObject object:objects) {
//                            ParseFile file = (ParseFile) object.get("image");
//                            Log.i("url address",file.getUrl().toString());
//                            file.getDataInBackground(new GetDataCallback() {
//                                @Override
//                                public void done(byte[] data, ParseException e) {
//                                    if(e == null && data != null) {
//                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
//                                        Bitmap.createScaledBitmap(bitmap, 150, 150, false);
//
//                                        ImageView image = new ImageView(getApplicationContext());
////                                        image.setLayoutParams(new ViewGroup.LayoutParams(
////                                                700,
////                                                ViewGroup.LayoutParams.WRAP_CONTENT
////                                        ));
//                                        image.setImageBitmap(bitmap);
//                                        images.add(image);
//
//                                        //gridView.setAdapter(new ImageAdapter(getApplicationContext(),images));
//
//                                    }
//                                }
//                            });
//
//                        }
//                    }
//                }
//            }
//        });
        ///////
        //Log.i("images size here", String.valueOf(images.size()));




        String activeUser = ParseUser.getCurrentUser().getUsername();
        setTitle(activeUser + "'s Feed");

        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("ProfileImage");
        query.whereEqualTo("username",activeUser);
        query.addDescendingOrder("createdAt");

        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    if(objects.size() > 0) {

                            ParseFile file = (ParseFile) objects.get(0).get("profileimage");
                            file.getDataInBackground(new GetDataCallback() {
                                @Override
                                public void done(byte[] data, ParseException e) {
                                    if(e == null && data != null) {

                                        Bitmap bitmap = BitmapFactory.decodeByteArray(data,0,data.length);
                                        Bitmap.createScaledBitmap(bitmap, 150, 150, false);
                                        ImageView profileImage = (ImageView) findViewById(R.id.profileImage);
                                        profileImage.setImageBitmap(bitmap);

                                    }
                                }
                            });


                    }
                }
            }
        });


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
        getMenuInflater().inflate(R.menu.share_menu,menu);
        //getMenuInflater().inflate(R.menu.myprofile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.share) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},1);
                } else {
                    getPhoto();
                }
            } else {
                getPhoto();
            }
        } else if( item.getItemId() == R.id.logout) {

            ParseUser.logOut();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);

        } else if (item.getItemId() == R.id.myprofile) {
                finish();
            //imageArray.clear();
            startActivity(getIntent());
        } else if(item.getItemId() == R.id.userList) {
            Intent intent = new Intent(getApplicationContext(),userListActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        //imageUpdate();
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onRestart(){
        super.onRestart();
        // put your code here...
        //imageUpdate();
        adapter.notifyDataSetChanged();
    }




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
            changeProfile();
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            try {
                Bitmap pic = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);

                Log.i("photo","received");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                pic.compress(Bitmap.CompressFormat.PNG,100,stream); // important
                byte[] byteArray = stream.toByteArray();
                ParseFile file = new ParseFile(byteArray);
                ParseObject object = new ParseObject("Image");

                object.put("image",file);
                object.put("username",ParseUser.getCurrentUser().getUsername());

                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null) {
                            Toast.makeText(getApplicationContext(),"Image saved successsfully",Toast.LENGTH_SHORT).show();
                            imageUpdate();
                        } else {
                            Toast.makeText(getApplicationContext(),"Image not saved. Try again!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestCode == 2 && resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            try {
                Bitmap pic = MediaStore.Images.Media.getBitmap(this.getContentResolver(),selectedImage);

                Log.i("profile photo","received");
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                pic.compress(Bitmap.CompressFormat.PNG,100,stream); // important
                byte[] byteArray = stream.toByteArray();
                ParseFile file = new ParseFile(byteArray);
                ParseObject object = new ParseObject("ProfileImage");

                object.put("profileimage",file);
                object.put("username",ParseUser.getCurrentUser().getUsername());

                object.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null) {
                            Toast.makeText(getApplicationContext(),"Profile Image saved successsfully",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Profile Image not saved. Try again!",Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
                ImageView profileImage = (ImageView) findViewById(R.id.profileImage);
                profileImage.setImageBitmap(pic);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}




class ImageAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<String> urls = new ArrayList<>();

    public ImageAdapter(final Context c,ArrayList<String> urls) {

        this.urls = urls;
        this.mContext = c;
    }

    public int getCount() {
        return urls.size();
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
                .resize(600,600).centerCrop()
                .into(imageView);

        return imageView;
    }
}


