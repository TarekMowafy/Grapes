package com.test.grapes;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;

import com.etsy.android.grid.StaggeredGridView;
import com.test.grapes.API.ApiService;
import com.test.grapes.Models.Product;

import java.util.LinkedList;
import java.util.List;


public class Home extends Activity implements AbsListView.OnScrollListener, AbsListView.OnItemClickListener {


    StaggeredGridView stgv;
    GRIDAdapter mAdapter;
    public static int FROM ;     //Starting value for fetching data from server
    public List<Product> PRODUCTS_LIST = new LinkedList<Product>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FROM = 0;
        stgv = (StaggeredGridView) findViewById(R.id.grid_view);
        mAdapter = new GRIDAdapter(this,R.id.imgView,PRODUCTS_LIST);
        stgv.setOnScrollListener(this);

        new LoadProducts().execute();

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_IDLE) {
            if (view.getLastVisiblePosition() >= view.getCount() - 1 - 0) {
                FROM+=10;
                //load more list items:
                new LoadProducts().execute();
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }


    private class LoadProducts extends AsyncTask<String ,Integer, List<Product>>{

        @Override
        protected List<Product> doInBackground(String... params) {

            ApiService apiService = new ApiService();
            return apiService.GetAllProducts(FROM);
        }

        @Override
        protected void onPostExecute(List<Product> results) {

            StringBuilder sb = new StringBuilder();
            for(Product product: results)
            {
                sb.append(product.getImage().getUrl() + "\n");
                PRODUCTS_LIST.add(product);
            }

            mAdapter.getItems(PRODUCTS_LIST);
            mAdapter.notifyDataSetChanged();
            stgv.setAdapter(mAdapter);
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
