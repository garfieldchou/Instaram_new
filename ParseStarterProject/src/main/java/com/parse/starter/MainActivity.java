/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import org.w3c.dom.Text;

import java.util.List;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  @Override
  public void onClick(View view) {

    if (view.getId() == R.id.changeSignupModeTextView) {

      Log.i("AppInfo", "Change Signup Mode");

    }

  }

  public void signUp (View view) {

    EditText usernameEditText = (EditText) findViewById(R.id.usernameEditText);

    EditText passwordEditText = (EditText) findViewById(R.id.passwordEditText);

    if (usernameEditText.getText().toString().equals("") || passwordEditText.getText().toString().equals("")) {

      Toast.makeText(this, "A username or password are required", Toast.LENGTH_SHORT).show();

    } else {

      ParseUser user = new ParseUser();

      user.setUsername(usernameEditText.getText().toString());

      user.setPassword(passwordEditText.getText().toString());

      user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {

          if (e == null) {

            Log.i("Signup", "Successful");

          } else {

            Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

          }
        }
      });

    }

  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    TextView changeSignupModeTextView = (TextView) findViewById(R.id.changeSignupModeTextView);

    changeSignupModeTextView.setOnClickListener(this);

    ParseUser.logOut();
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}
