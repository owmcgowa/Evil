package com.example.owmcgowa_adm.evil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;


/**
 * Created by owmcgowa-adm on 9/2/2015.
 */
public class Login extends AppCompatActivity implements View.OnClickListener{


    public static final String USER_NAME = "USER_NAME";
    public static final String PASSWORD = "PASSWORD";
    private static final String LOGIN_URL = "http://141.219.187.99/evil/login.php";


    EditText etSuperUsername;
    EditText etSuperPassword;

    Button bLogin;
    Button bRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etSuperUsername = (EditText)findViewById(R.id.etSuperUsername);
        etSuperPassword = (EditText)findViewById(R.id.etSuperPassword);

        bLogin = (Button)findViewById(R.id.bLogin);
        bRegister = (Button)findViewById(R.id.bRegister);

        bLogin.setOnClickListener(this);
        bRegister.setOnClickListener(this);


    }

    private void login() {
        String username = etSuperUsername.getText().toString().trim();
        String password = etSuperPassword.getText().toString().trim();
        userLogin(username, password);
    }

    private void userLogin(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Login.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("success")){
                    Intent intent = new Intent(Login.this,Home.class);
                    intent.putExtra(USER_NAME,username);
                    startActivity(intent);
                }else{
                    Toast.makeText(Login.this,s,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected String doInBackground(String... params) {
                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);

                RegisterUserClass r = new RegisterUserClass();

                String result = r.sendPostRequest(LOGIN_URL,data);

                return result;
            }
        }
        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username, password);
    }

    public void onClick(View v){

        if(v == bLogin){
            login();
            }
        if(v == bRegister){
            Intent i = new Intent(Login.this, SignUp.class);
            startActivity(i);
        }

    }

}
