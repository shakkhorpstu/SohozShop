package com.example.shakkhor.sohozshopping;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Product_List_Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    String customer_username;
    String phone = "01710000000";

    //private AlertDialog.Builder adb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product__list_);

        // getSupportActionBar().setTitle("Product List");

        listView = (ListView) findViewById(R.id.product_list);

        fetchingdata();

        customer_username = getIntent().getStringExtra("username");


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        getMenuInflater().inflate(R.menu.product__list_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_products:
                Intent intent = new Intent(Product_List_Activity.this, OrderItem_Activity.class);
                intent.putExtra("username", customer_username);
                startActivity(intent);
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.profile) {
            // Handle the camera action
        } else if (id == R.id.change_password) {
            Intent intent = new Intent(Product_List_Activity.this,Change_password.class);
            startActivity(intent);
        } else if (id == R.id.logout) {

        } else if (id == R.id.about) {

        }
        else if (id == R.id.call) {
            //Intent intent = new Intent(Intent.ACTION_CALL);
            //intent.setData(Uri.parse(phone));
            //if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                //return TODO;
            //}
            //startActivity(intent);
        }

        //else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {
//
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

     void fetchingdata(){

        String my_url = "http://192.168.43.79/sohozshopping/product_details.php";
         //adb = new AlertDialog.Builder(this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(my_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                final String[] product_id = new String[response.length()];
                final String[] product_name = new String[response.length()];
                final String[] product_price = new String[response.length()];
                final String[] product_description = new String[response.length()];
                final String[] product_image = new String[response.length()];

                for(int i = 0; i<response.length(); i++){
                    try {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        product_id[i] = jsonObject.getString("id");
                        product_name[i] = jsonObject.getString("product_name");
                        product_price[i] = jsonObject.getString("product_price");
                        product_description[i] = jsonObject.getString("product_description");
                        product_image[i] = jsonObject.getString("product_image");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


//                adb.setMessage(response.toString()+"\n\n"+product_name[2]).setTitle("ddd")
//                    .setCancelable(true)
//                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//                AlertDialog ad = adb.create();
//                //ad.show();

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.product_list_layout,R.id.product_name,product_name);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(Product_List_Activity.this,Details.class);
                        intent.putExtra("id",product_id[position]);
                        intent.putExtra("Product_name",product_name[position]);
                        intent.putExtra("Product_price",product_price[position]);
                        intent.putExtra("Product_description",product_description[position]);
                        intent.putExtra("username",customer_username);
                        intent.putExtra("Product_image",product_image[position]);
                        startActivity(intent);
                    }
                });

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Volley Log ",error);
            }
        });

        com.example.shakkhor.sohozshopping.AppController.getInstance().addToRequestQueue(jsonArrayRequest);


    }

}
