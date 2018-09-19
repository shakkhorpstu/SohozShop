package com.example.shakkhor.sohozshopping;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Registration_Activity extends AppCompatActivity {

    EditText etfirstname,etlastname,etusername,etpassword,etcity,etaddress;
    Button submit;

    private static final String URL = "http://192.168.43.79/sohozshopping/register.php";
    static final String FIRST_NAME = "first_name";
    static final String LAST_NAME = "last_name";
    static final String USERNAME = "username";
    static final String PASSWORD = "password";
    static final String CITY = "city";
    static final String FULL_ADDRESS = "full_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_);

        getSupportActionBar().setTitle("Registration");

        etfirstname = (EditText) findViewById(R.id.editTextFirstName);
        etlastname = (EditText) findViewById(R.id.editTextLastName);
        etusername = (EditText) findViewById(R.id.editTextUserName);
        etpassword = (EditText) findViewById(R.id.editTextPassword);
        etcity = (EditText) findViewById(R.id.editTextCity);
        etaddress = (EditText) findViewById(R.id.editTextAddress);
        submit = (Button) findViewById(R.id.buttonRegister);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String first_name = etfirstname.getText().toString().trim();
                final String last_name = etlastname.getText().toString().trim();
                final String username = etusername.getText().toString().trim();
                final String password = etpassword.getText().toString().trim();
                final String city = etcity.getText().toString().trim();
                final String full_address = etaddress.getText().toString().trim();


                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Log.d("RESPONSE", "" + response);
                                Toast.makeText(Registration_Activity.this, "" + response, Toast.LENGTH_LONG).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //Toast.makeText(Registration_Activity.this, error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put(FIRST_NAME, first_name);
                        params.put(LAST_NAME, last_name);
                        params.put(USERNAME, username);
                        //params.put(EMAIL, email);
                        params.put(CITY, city);
                        params.put(FULL_ADDRESS, full_address);
                        params.put(PASSWORD, password);

                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

                finish();

            }
        });

    }
}
