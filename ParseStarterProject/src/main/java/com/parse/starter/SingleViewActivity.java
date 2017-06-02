package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SingleViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);
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

        ;
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("position");
        setTitle(i.getExtras().getString("username") + "'s feed");
        ImageAdapter imageAdapter = new ImageAdapter(this,new ArrayList<>(myprofileActivity.imageArray));

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        TextView date = (TextView) findViewById(R.id.date);
        date.setText(i.getExtras().getString("createdAt"));
        Picasso.with(this)
                .load(imageAdapter.urls.get(position))
                .resize(600,700).centerCrop()
                .into(imageView);

    }

}
