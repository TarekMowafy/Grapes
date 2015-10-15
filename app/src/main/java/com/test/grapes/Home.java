package com.test.grapes;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.grapes.API.ApiService;
import com.test.grapes.Models.Product;
import com.test.grapes.StaggeredGridView.StaggeredGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;


public class Home extends Activity {


    StaggeredGridView stgv;
    GRIDAdapter mAdapter;
    boolean isAdapterSet= false;

    public static int FROM = 0;     //Starting value for fetching data from server
    public static String URL = "http://grapesnberries.getsandbox.com/products?count=10&from=";
    public List<Product> PRODUCTS_LIST = new LinkedList<Product>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        stgv = (StaggeredGridView) findViewById(R.id.stgv);

        int margin = getResources().getDimensionPixelSize(R.dimen.stgv_margin);

        stgv.setItemMargin(margin);
        stgv.setPadding(margin, 0, margin, 0);

        View footerView;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        footerView = inflater.inflate(R.layout.layout_loading_footer, null);
        stgv.setFooterView(footerView);

        mAdapter = new GRIDAdapter(this, getApplication());

        new LoadProducts().execute();

        stgv.setOnLoadmoreListener(new StaggeredGridView.OnLoadmoreListener() {
            @Override
            public void onLoadmore() {
                FROM =FROM+10;
                new LoadProducts().execute();
            }
        });
    }

    private class LoadProducts extends AsyncTask<String ,Integer, List<Product>>{



        @Override
        protected void onPreExecute() {


        }

        @Override
        protected List<Product> doInBackground(String... params) {

            ApiService apiService = new ApiService();
            return apiService.GetAllProducts(FROM);
        }

        @Override
        protected void onPostExecute(List<Product> results) {

            StringBuilder sb = new StringBuilder();
            Log.d("Products Output: ", String.valueOf(results.size()));
            for(Product product: results)
            {
                Log.d("Products Output: ", product.getImage().getUrl());
                sb.append(product.getImage().getUrl() + "\n");
                PRODUCTS_LIST.add(product);
            }
            Log.d("Products Output: ", String.valueOf(PRODUCTS_LIST.size()));


            mAdapter.getMoreItem(PRODUCTS_LIST,FROM);
            if(!isAdapterSet){
                stgv.setAdapter(mAdapter);
                mAdapter.notifyDataSetChanged();
                isAdapterSet=true;
            }else{
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
