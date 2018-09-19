package com.example.shakkhor.sohozshopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Change_password extends AppCompatActivity {

    EditText old_pass,new_pass,con_new_pass;
    Button change_pass;
    private static final String URL = "http://192.168.43.79/sohozshopping/change_password.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        old_pass = (EditText) findViewById(R.id.old_pass);
        new_pass = (EditText) findViewById(R.id.new_pass);
        con_new_pass = (EditText) findViewById(R.id.con_new_pass);
        change_pass = (Button) findViewById(R.id.change_pass);

        change_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                change_password();
            }
        });

    }

    void change_password(){
        final String old_password = old_pass.getText().toString().trim();
        final String new_password = new_pass.getText().toString().trim();
        final String confirm_password = con_new_pass.getText().toString().trim();

        if(new_password.equals(con_new_pass)){
            Toast.makeText(this, "Unmatched Password", Toast.LENGTH_SHORT).show();
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("RESPONSE", "" + response);
                        Toast.makeText(Change_password.this, "" + response, Toast.LENGTH_LONG).show();
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
                params.put("new_password", new_password);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

        finish();
    }

}
