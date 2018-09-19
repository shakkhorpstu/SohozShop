package com.example.shakkhor.sohozshopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Edit_Order extends AppCompatActivity {

    TextView prodt_name,prodt_price;
    Button remove;
    String customer_username;
    private static final String URL = "http://192.168.43.79/sohozshopping/edit_order.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__order);

        getSupportActionBar().setTitle("Edit Cart");

        prodt_name = (TextView) findViewById(R.id.prdt_name);
        prodt_price = (TextView) findViewById(R.id.prdt_price);
        remove = (Button) findViewById(R.id.remove);

        final String prdt_id = getIntent().getStringExtra("id");
        String prdt_name = getIntent().getStringExtra("product_name");
        String prdt_price = getIntent().getStringExtra("product_price");

        customer_username = getIntent().getStringExtra("username");

        prodt_name.setText(prdt_name);
        prodt_price.setText(prdt_price);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Log.d("RESPONSE", "" + response);
                                Toast.makeText(Edit_Order.this, "" + response, Toast.LENGTH_LONG).show();
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
                        params.put("product_id", prdt_id);
                        params.put("username", customer_username);

                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

                Intent intent = new Intent(Edit_Order.this,OrderItem_Activity.class);
                startActivity(intent);

            }
        });

    }
}
