package com.example.owmcgowa_adm.evil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by owmcgowa-adm on 9/8/2015.
 */
public class Home extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        textView = (TextView)findViewById(R.id.textView3);

        Intent intent = getIntent();

        String username = intent.getStringExtra(Login.USER_NAME);

        textView.setText("Welcome User " + username);
    }


}
