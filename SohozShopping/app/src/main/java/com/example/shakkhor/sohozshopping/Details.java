package com.example.shakkhor.sohozshopping;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Details extends AppCompatActivity {

    TextView t_product_name,t_product_description,t_product_price;
    ImageView img_product;
    Button add_to_cart;
    private static final String URL = "http://192.168.43.79/sohozshopping/product_shopping.php";
    String product_id,product_name,product_description,product_price,username,img;

    private RequestQueue mRequestQueue;
    private ImageLoader imageLoader;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        getSupportActionBar().setTitle("Product Details");

        t_product_name = (TextView) findViewById(R.id.product_name);
        t_product_description = (TextView) findViewById(R.id.product_description);
        t_product_price = (TextView) findViewById(R.id.product_price);
        add_to_cart = (Button) findViewById(R.id.add_to_cart);
        img_product = (ImageView) findViewById(R.id.img_product);

        product_id = getIntent().getStringExtra("id");
        product_name = getIntent().getStringExtra("Product_name");
        product_description = getIntent().getStringExtra("Product_description");
        product_price = getIntent().getStringExtra("Product_price");
        img = getIntent().getStringExtra("Product_image");

        //username = getIntent().getStringExtra("username");

        //product_id.setText(p_id);
        t_product_name.setText(product_name);
        t_product_description.setText(product_description);
        t_product_price.setText(product_price+" TK");

        if(img==null) Toast.makeText(getApplicationContext(), "Empty url", Toast.LENGTH_SHORT).show();
        else {
            setUserImage(img);
        }


        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //product_add();


                username = getIntent().getStringExtra("username");
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Log.d("RESPONSE", "" + response);
                                Toast.makeText(Details.this, "" + response, Toast.LENGTH_LONG).show();
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
                        params.put("product_id", product_id);
                        params.put("product_name", product_name);
                        params.put("product_price", product_price);
                        params.put("username",username);

                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

                finish();

            }
        });

    }

    private void setUserImage(String url){
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        imageLoader = new ImageLoader(mRequestQueue,
                new LruBitmapCache());
        bitmap = null;

            imageLoader.get(url, new ImageLoader.ImageListener() {
                @Override
                public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {

                    if(isImmediate){
                        Toast.makeText(getApplicationContext(), "Loading image", Toast.LENGTH_SHORT).show();
                    }else {
                        bitmap = response.getBitmap();
                        if(bitmap!=null){
                            img_product.setImageBitmap(bitmap);
                            //Toast.makeText(getApplicationContext(), "Image found", Toast.LENGTH_SHORT).show();
                        }
                        else Toast.makeText(getApplicationContext(), "Image not found", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("Volly image loader", "Volley Error: "+error.getMessage());
                }
            });
    }

//    void product_add(){
//
//    }

}
