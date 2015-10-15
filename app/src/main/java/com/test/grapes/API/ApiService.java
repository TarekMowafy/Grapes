package com.test.grapes.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.test.grapes.Models.Product;


import java.lang.String;
import java.util.List;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by tarek on 10/15/15.
 */
public class ApiService {
    private String url = "http://grapesnberries.getsandbox.com";


    public interface GetAllAPI{
        @GET("/products")
        List<Product> getProds(@Query("count") int count, @Query("from") int from);

    }

    public List<Product> GetAllProducts(int from){
        Gson gson = new GsonBuilder().create();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(url)
                .setConverter(new GsonConverter(gson))
                .build();

        GetAllAPI service = restAdapter.create(GetAllAPI.class);

        List<Product> productList = service.getProds(10,from);
        return productList;
    }

}
