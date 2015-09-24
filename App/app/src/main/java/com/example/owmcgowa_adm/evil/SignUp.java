package com.example.owmcgowa_adm.evil;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by owmcgowa-adm on 9/2/2015.
 */
public class SignUp extends AppCompatActivity implements View.OnClickListener {

    EditText etFirstName;
    EditText etLastName;
    EditText etEmail;
    EditText etSuperUsername;
    EditText etSuperPassword;
    EditText etConfirmPassword;
    EditText etBirthday;
    EditText etWeight;
    EditText etHeight;

    RadioButton rbMale;
    RadioButton rbFemale;
    RadioButton rbOther;

    Button bCreate;

    private static final String REGISTER_URL = "http://141.219.187.99/evil/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        etFirstName = (EditText) findViewById(R.id.etFirstName);
        etLastName = (EditText) findViewById(R.id.etLastName);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSuperUsername = (EditText) findViewById(R.id.etSuperUsername);
        etSuperPassword = (EditText) findViewById(R.id.etSuperPassword);
        etConfirmPassword = (EditText) findViewById(R.id.etConfirmPassword);
        etBirthday = (EditText) findViewById(R.id.etBirthday);
        etWeight = (EditText) findViewById(R.id.etWeight);
        etHeight = (EditText) findViewById(R.id.etHeight);

        rbMale = (RadioButton) findViewById(R.id.rbMale);
        rbFemale = (RadioButton) findViewById(R.id.rbFemale);
        rbOther = (RadioButton) findViewById(R.id.rbOther);

        bCreate = (Button) findViewById(R.id.bCreate);

        bCreate.setOnClickListener(this);

    }

    public void onClick(View v) {

        if (v == bCreate) {
            registerUser();
        }
    }

    private void registerUser() {

        String first = etFirstName.getText().toString().trim().toLowerCase();
        String last = etLastName.getText().toString().trim().toLowerCase();
        String email = etEmail.getText().toString().trim().toLowerCase();
        String superusername = etSuperUsername.getText().toString().trim().toLowerCase();
        String superpassword = etSuperPassword.getText().toString().trim().toLowerCase();
        String birthday = etBirthday.getText().toString();
        String male = rbMale.getText().toString();
        String female = rbFemale.getText().toString();
        String other = rbOther.getText().toString();
        String weight = etWeight.getText().toString();
        String height = etHeight.getText().toString();

        register(first, last, email, superusername, superpassword, birthday, male, female, other, weight, height);

    }

    private void register(String first, String last, String email, String superusername, String superpassword, String birthday, String male, String female, String other, String weight, String height) {

        String urlSuffix = "?first=" + first + "&last=" + last + "&email" + email + "&superusername" + superusername+ "&superpassword=" + superpassword + "&birthday=" + birthday + "&male=" + male+ "&female=" + female + "&other=" + other + "&weight=" + weight + "&height=" + height;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(SignUp.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(REGISTER_URL + s);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String result;

                    result = bufferedReader.readLine();

                    return result;
                } catch (Exception e) {
                    return null;
                }
            }
        }

        RegisterUser r = new RegisterUser();
        r.execute(urlSuffix);
    }
}