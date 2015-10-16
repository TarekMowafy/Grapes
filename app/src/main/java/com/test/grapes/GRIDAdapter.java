package com.test.grapes;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightImageView;
import com.squareup.picasso.Picasso;
import com.test.grapes.Models.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class GRIDAdapter extends ArrayAdapter {
    private Context mContext;
    public List<Product> mProds = new LinkedList<Product>();
    private final LayoutInflater mLayoutInflater;

    public GRIDAdapter(Context context,int textViewResourceId, List objects) {
        super(context, textViewResourceId, objects);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }
    public void getItems(List<Product> PRODUCTS_LIST) {

        mProds = PRODUCTS_LIST;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Product product = mProds.get(position);
        Holder holder;

        if (convertView == null) {
            holder = new Holder();
            LayoutInflater li = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.product_item, null);

            holder.img_content = (DynamicHeightImageView) convertView.findViewById(R.id.imgView);
            holder.tv_price = (TextView) convertView.findViewById(R.id.tv_Price);
            holder.tv_desc = (TextView) convertView.findViewById(R.id.tv_description);

            convertView.setTag(holder);

        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.tv_price.setText("$ " + String.valueOf(product.getPrice()));
        holder.tv_desc.setText(product.getProductDescription());


        Picasso.with(mContext).setIndicatorsEnabled(true);
        Picasso.with(mContext)
                .load(product.getImage().getUrl())
                .into(holder.img_content);

        double positionHeightRatio = product.getImage().getHeight()/product.getImage().getWidth();
        holder.img_content.setHeightRatio(positionHeightRatio);

        return convertView;
    }

    class Holder {
        DynamicHeightImageView img_content;
        TextView tv_price;
        TextView tv_desc;
    }

}