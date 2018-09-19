package com.example.shakkhor.sohozshopping;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Start_Activity extends AppCompatActivity {

    Button login,registration;
    public static final String USER_NAME = "USER_NAME";
    public static final String PASSWORD = "PASSWORD";

    static final String ip_add = "192.168.43.79";
    static final String LOGIN_URL="http://"+ip_add+"/sohozshopping/login.php";

    private EditText editTextUserName;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_);

        getSupportActionBar().setTitle("Sign In");

        editTextUserName = (EditText) findViewById(R.id.input_username);
        editTextPassword = (EditText) findViewById(R.id.input_password);
        login = (Button) findViewById(R.id.btn_login);
        registration = (Button) findViewById(R.id.btn_registration);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Start_Activity.this,Registration_Activity.class);
                startActivity(intent1);
            }
        });

    }

    private void Login(){
        String username = editTextUserName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        userLogin(username,password);
    }

    private void userLogin(final String username, final String password){
        class UserLoginClass extends AsyncTask<String,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Start_Activity.this,"Please Wait",null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                if(s.equalsIgnoreCase("success")){
                    Intent intent = new Intent(Start_Activity.this,Product_List_Activity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                }else{
                    Toast.makeText(Start_Activity.this,s,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                HashMap<String,String> data = new HashMap<>();
                data.put("username",params[0]);
                data.put("password",params[1]);

                RegisterUserClass ruc = new RegisterUserClass();

                String result = ruc.sendPostRequest(LOGIN_URL,data);

                return result;
            }
        }

        UserLoginClass ulc = new UserLoginClass();
        ulc.execute(username,password);

    }

}
