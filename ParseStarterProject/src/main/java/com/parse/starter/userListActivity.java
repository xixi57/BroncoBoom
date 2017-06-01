package com.parse.starter;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class userListActivity extends AppCompatActivity {

    public ListView userList;
    public ArrayAdapter<String> adapter;
    public static ArrayList<String> users;

    public void getPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);//importante!!
        startActivityForResult(intent,1);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1) {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getPhoto();
            }
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.share_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
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
            Intent intent = new Intent(getApplicationContext(), myprofileActivity.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.userList)
        {
            Intent intent = new Intent(getApplicationContext(),userListActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        userList = (ListView) findViewById(R.id.userListView);
        users = new ArrayList<>();

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
        query.addAscendingOrder("username");
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> objects, ParseException e) {
                if(e == null) {
                    Log.i("retrived users",Integer.toString(objects.size()));
                    users.clear();
                    for(ParseUser user: objects) {
                        Log.i("user name is", user.getString("username"));
                        users.add(user.getUsername().toString());
                        Log.i("userslist length",String.valueOf(users.size()));
                    }
                    ArrayList<String> activeusers  = new ArrayList<String>(users);
                    adapter = new ArrayAdapter<String>(userListActivity.this, android.R.layout.simple_list_item_1, activeusers);
                    userList.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } else {
                    Log.i("users retrive failed","noo!!!");
                }
            }
        });

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(userListActivity.this,userFeedScrollingActivity.class);
                intent.putExtra("username",users.get(position));
                startActivity(intent);
            }
        });



       // ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }
    @Override
    public void onResume(){
        super.onResume();
        // put your code here...
        //imageUpdate();
//        userList = (ListView) findViewById(R.id.userListView);
//
//
//        ParseQuery<ParseUser> query = ParseUser.getQuery();
//        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
//        query.addAscendingOrder("username");
//        query.findInBackground(new FindCallback<ParseUser>() {
//            @Override
//            public void done(List<ParseUser> objects, ParseException e) {
//                if(e == null) {
//                    Log.i("retrived users",Integer.toString(objects.size()));
//                    for(ParseUser user: objects) {
//                        users = new ArrayList<>();
//                        users.add(user.getUsername().toString());
//                        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, users);
//                        userList.setAdapter(adapter);
//                    }
//                } else {
//                    Log.i("users retrive failed","noo!!!");
//                }
//            }
//        });

    }

    @Override
    public void onRestart(){
        super.onRestart();
        // put your code here...
        //imageUpdate();

//        userList = (ListView) findViewById(R.id.userListView);
//
//
//        ParseQuery<ParseUser> query = ParseUser.getQuery();
//        query.whereNotEqualTo("username",ParseUser.getCurrentUser().getUsername());
//        query.addAscendingOrder("username");
//        query.findInBackground(new FindCallback<ParseUser>() {
//            @Override
//            public void done(List<ParseUser> objects, ParseException e) {
//                if(e == null) {
//                    Log.i("retrived users",Integer.toString(objects.size()));
//                    for(ParseUser user: objects) {
//                        users = new ArrayList<>();
//                        users.add(user.getUsername().toString());
//                        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, users);
//                        userList.setAdapter(adapter);
//                    }
//                } else {
//                    Log.i("users retrive failed","noo!!!");
//                }
//            }
//        });


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
                        } else {
                            Toast.makeText(getApplicationContext(),"Image not saved. Try again!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
