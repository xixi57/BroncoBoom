package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class userFeedScrollingActivity extends AppCompatActivity {

    public static ArrayList<String> userimagearray;

    public ImageAdapter userimageadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_feed_scrolling);
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

        userimagearray = new ArrayList<>();
        final GridView usergridview = (GridView) findViewById(R.id.userFeedGridView);
        Intent intent = getIntent();
        String activeUser = intent.getStringExtra("username");
        setTitle(activeUser + "'s Feed");


        ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Image");
        query.whereEqualTo("username",activeUser);
        query.addDescendingOrder("createdAt");


        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if(e == null) {
                    if(objects.size() > 0) {
                        Log.i("number of image",String.valueOf(objects.size()));
                        for(ParseObject object:objects) {
                            ParseFile file = (ParseFile) object.get("image");
                            userimagearray.add(file.getUrl().toString());
                        }
                        ArrayList<String>urls = new ArrayList<>(userimagearray);
                        // GridView gridView = (GridView) findViewById(R.id.myGridView);
                        Log.i("oncreat called","urls size is" + String.valueOf(urls.size()));
                        userimageadapter = new ImageAdapter(getApplicationContext(), urls);
                        usergridview.setAdapter(userimageadapter);
                        //imageUpdate();
                        userimageadapter.notifyDataSetChanged();
                    }
                }
            }
        });






        
    }
}



