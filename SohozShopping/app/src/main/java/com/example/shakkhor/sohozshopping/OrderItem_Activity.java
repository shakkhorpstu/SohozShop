package com.example.shakkhor.sohozshopping;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OrderItem_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    TextView total_amount;
    Button confirm;
    private static final String URL = "http://192.168.43.79/sohozshopping/confirm_shopping.php";
    String my_url = "http://192.168.43.79/sohozshopping/unconfirm_shopping.php";
    String customer_username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_item_);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Cart");


        confirm = (Button) findViewById(R.id.confirm);
        listView = (ListView) findViewById(R.id.order_listview);
        total_amount = (TextView) findViewById(R.id.total_price);

        customer_username = getIntent().getStringExtra("username");

        fetchinng_data();



        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm_shopping();
                Intent intent = new Intent(OrderItem_Activity.this,OrderItem_Activity.class);
                startActivity(intent);
            }
        });




//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.order_item_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_checkout) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profile) {
            // Handle the camera action
        } else if (id == R.id.change_password) {

        } else if (id == R.id.logout) {

        } else if (id == R.id.about) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    private int totalPrice=0;
    void fetchinng_data(){
        my_url=my_url+"?username="+customer_username;

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(my_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final String[] product_name = new String[response.length()];
                final String[] product_price = new String[response.length()];
                final String[] product_id = new String[response.length()];

                for(int i = 0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        product_name[i] = jsonObject.getString("product_name");
                        product_id[i] = jsonObject.getString("product_id");
                        product_price[i] = jsonObject.getString("product_price");
                        totalPrice+=Integer.parseInt(jsonObject.getString("product_price"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //Toast.makeText(OrderItem_Activity.this, ""+totalPrice, Toast.LENGTH_SHORT).show();

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.order_list_layout,R.id.product_name,product_name);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(OrderItem_Activity.this,Edit_Order.class);
                        intent.putExtra("id",product_id[position]);
                        intent.putExtra("product_name",product_name[position]);
                        intent.putExtra("product_price",product_price[position]);
                        intent.putExtra("username",customer_username);
                        startActivity(intent);
                    }
                });
                String t_price = Integer.toString(totalPrice);
                total_amount.setText("Total: "+t_price+" TK");
                //total_amount.setText(Integer.toString(totalPrice));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log ",error);
            }
        });



        com.example.shakkhor.sohozshopping.AppController.getInstance().addToRequestQueue(jsonArrayRequest);

    }


    void confirm_shopping(){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //Log.d("RESPONSE", "" + response);
                        Toast.makeText(OrderItem_Activity.this, "" + response, Toast.LENGTH_LONG).show();
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
                params.put("username", customer_username);

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}
