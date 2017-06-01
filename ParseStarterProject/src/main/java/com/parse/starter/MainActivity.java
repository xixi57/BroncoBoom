/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,View.OnKeyListener {


    public void showUserList() {
        Intent intent = new Intent(getApplicationContext(),userListActivity.class);
        startActivity(intent);
    }
    Boolean newUser = false;//click sign up as a false, click login as a true; old user as default
    TextView username;
    TextView userpassword;
    public void signupClick(View view) {
        if(newUser) {
            newUser = false;
            Button buttonView = (Button) findViewById(R.id.loginButton);
            buttonView.setText("log in!");
            TextView signupView = (TextView) findViewById(R.id.signupView);
            signupView.setText("or, sign up!");
            //Log.i("new user?","false");

        } else {
            newUser = true;
            Button buttonView = (Button) findViewById(R.id.loginButton);
            buttonView.setText("sign up!");
            TextView signupView = (TextView) findViewById(R.id.signupView);
            signupView.setText("or, log in!");
           // Log.i("new user?","true");
        }
    }

    public void loginClick(View view) {
        username = (TextView) findViewById(R.id.userName);
        userpassword = (TextView) findViewById(R.id.userPassword);

        if(username.getText().toString().matches("") || username.getText().toString().matches("")) {
            Toast.makeText(getApplicationContext(), "please complete the username and password!", Toast.LENGTH_SHORT).show();
        } else {

            if (!newUser) {// want to log in

                ParseUser.logInInBackground(username.getText().toString(), userpassword.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), user.getString("username") + ", you login successfully!", Toast.LENGTH_SHORT).show();
                            showUserList();
                        } else {
                            Toast.makeText(getApplicationContext(), "oops, try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                final ParseUser newuser = new ParseUser();
                newuser.setUsername(username.getText().toString());
                newuser.setPassword(userpassword.getText().toString());
                newuser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if (e == null) {
                            Toast.makeText(getApplicationContext(), newuser.getString("username") + ", you signup successfully!", Toast.LENGTH_SHORT).show();
                            showUserList();
                        } else {
                            Toast.makeText(getApplicationContext(), "oops, already taken, try again!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }

        }
    }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      username = (TextView) findViewById(R.id.userName);
      userpassword = (TextView) findViewById(R.id.userPassword);

      RelativeLayout signupRelativeView = (RelativeLayout) findViewById(R.id.signupRelativeLayout);
      ImageView logoView = (ImageView) findViewById(R.id.logoView);
      signupRelativeView.setOnClickListener(this);
      logoView.setOnClickListener(this);
        if(ParseUser.getCurrentUser() != null) {
            showUserList();
        }
      /*
      ParseUser user = new ParseUser();
      user.setUsername("Xi");
      user.setPassword("123");
      user.signUpInBackground(new SignUpCallback() {
          @Override
          public void done(ParseException e) {
              if(e == null) {
                  Log.i("signup","yay");
              } else {
                  Log.i("sigh up","failed");
              }
          }
      });

    */


//      ParseUser.logInInBackground("XiXi", "123", new LogInCallback() {
//          @Override
//          public void done(ParseUser user, ParseException e) {
//              if(e == null) {
//                  Log.i("login","yay");
//
//              } else {
//                  Log.i("login","failed");
//              }
//          }
//      });

      //ParseUser.logOut();
//      if(ParseUser.getCurrentUser() != null) {
//          Log.i("current user loggin",ParseUser.getCurrentUser().toString());
//      } else {
//          Log.i("current user loggin", "noe one");
//      }

//        ParseObject score = new ParseObject("Score");
//        score.put("name","Xi Wang");
//        score.put("score",76);
//
//        score.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//                if( e == null) {
//                    Log.i("save in background","successful!");
//                } else {
//                    Log.i("save in background","failred..");
//                }
//            }
//        });

/*
      final ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

      query.getInBackground("3VCOzOJFpN", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {
              object.put("score",120);
              Log.i("objectvalue",Integer.toString(object.getInt("score")));
              Log.i("objectvalue",object.getString("name"));

          }
      });

*/


//      ParseObject myTweet = new ParseObject("myTweet");
//      myTweet.put("name","Shelly");
//      myTweet.put("tweet","hello there!");
//
//      myTweet.saveInBackground(new SaveCallback() {
//          @Override
//          public void done(ParseException e) {
//              if(e == null) {
//                  Log.i("myTweet","saved successfully");
//
//              } else {
//                  Log.i("myTweet","not saved");
//              }
//          }
//      });


//      ParseQuery<ParseObject> query = ParseQuery.getQuery("myTweet");
//      query.getInBackground("LktGAmHhcD", new GetCallback<ParseObject>() {
//          @Override
//          public void done(ParseObject object, ParseException e) {
//              //object.put("tweet","hahahahahoo!");
//                  Log.i("myTweet",object.getString("name"));
//                  Log.i("myTweet",object.getString("tweet"));
//          }
//      });

 //     ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
//      query.findInBackground(new FindCallback<ParseObject>() {
//          @Override
//          public void done(List<ParseObject> objects, ParseException e) {
//              if(e == null) {
//                  Log.i("retrieved","size is" + Integer.toString(objects.size()));
//                  if(objects.size() > 0) {
//                      Log.i("objects are", objects.toString());
//                  }
//              }
//          }
//      });

//
//      query.getInBackground("FYSNENqWaF", new GetCallback<ParseObject>() {
//          @Override
//          public void done(ParseObject object, ParseException e) {
//
//              if(e == null) {
//                  object.put("tweet","hello xixi!");
//                  try {
//                      object.save();
//                  } catch (ParseException e1) {
//                      e1.printStackTrace();
//                  }
//                  Log.i("tweet is", object.getString("tweet"));
//              } else {
//                  Log.e("error is", e.toString());
//              }
//          }
//      });

//
//      query.whereGreaterThan("score",120);
//
//      query.findInBackground(new FindCallback<ParseObject>() {
//          @Override
//          public void done(List<ParseObject> objects, ParseException e) {
//                if(e == null) {
//                    Log.i("objects retrieved", Integer.toString(objects.size()));
//                    for(ParseObject object:objects) {
//                        //object.put("score",object.getInt("score") + 20);
//                        Log.i("cur object socre is", Integer.toString(object.getInt("score")));
//                        try {
//                            object.save();
//                        } catch (ParseException e1) {
//                            e1.printStackTrace();
//                        }
//                    }
//                }
//          }
//      });
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
            loginClick(v);
        }
        return false;
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.signupRelativeLayout || v.getId() == R.id.logoView) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
    }
}