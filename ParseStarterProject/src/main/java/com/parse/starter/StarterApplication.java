/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;


public class StarterApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(new Parse.Configuration.Builder(getApplicationContext())
            .applicationId("f92b7a419b84dc890cbce723cbdf89e5f58e8db9")
            .clientKey("1edfc2a1308252ef4aa55f675a6fe4d8638b6024")
            .server("http://ec2-52-10-214-108.us-west-2.compute.amazonaws.com:80/parse/")
            .build()
    );
    //bitnami: username: user
  //bitnami: password: A2LwX0pmDene
    //terminal start: ssh -v -i /Users/raoyinchen/Downloads/insandroid2.pem ubuntu@ec2-52-10-214-108.us-west-2.compute.amazonaws.com
//    ParseObject object = new ParseObject("ExampleObject");
//    object.put("myNumber", "123");
//    object.put("myString", "rob");
//
//    object.saveInBackground(new SaveCallback () {
//      @Override
//      public void done(ParseException ex) {
//        if (ex == null) {
//          Log.i("Parse Result", "Successful!");
//        } else {
//          Log.i("Parse Result", "Failed" + ex.toString());
//        }
//      }
//    });


    //ParseUser.enableAutomaticUser();

    ParseACL defaultACL = new ParseACL();
    defaultACL.setPublicReadAccess(true);
    defaultACL.setPublicWriteAccess(true);
    ParseACL.setDefaultACL(defaultACL, true);

  }
}
