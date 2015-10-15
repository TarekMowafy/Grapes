package com.test.grapes;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.LinkedList;
import java.util.List;

import com.test.grapes.Models.Product;
import com.test.grapes.StaggeredGridView.STGVImageView;

public class GRIDAdapter extends BaseAdapter {
    private Context mContext;
    private Application mAppContext;
    public List<Product> mProds = new LinkedList<Product>();

    public GRIDAdapter(Context context, Application app) {
        mContext = context;
        mAppContext = app;
    }
    public void getMoreItem(List<Product> PRODUCTS_LIST, int FROM) {

        mProds = PRODUCTS_LIST;
        Log.e("PRODUCt lISt size", String.valueOf(mProds.size()));

    }


    @Override
    public int getCount() {
        return mProds == null ? 0 : mProds.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;
        final Product product = mProds.get(position);

        if (convertView == null) {
            Holder holder = new Holder();
            view = View.inflate(mContext, R.layout.product_item, null);
            holder.img_content = (STGVImageView) view.findViewById(R.id.iv_image);
            holder.tv_price = (TextView) view.findViewById(R.id.tv_Price);
            holder.tv_desc = (TextView) view.findViewById(R.id.tv_description);

            view.setTag(holder);
        } else {
            view = convertView;
        }

        Holder holder = (Holder) view.getTag();
        /**
         * StaggeredGridView has bugs dealing with child TouchEvent
         * You must deal TouchEvent in the child view itself
         **/
        holder.img_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.tv_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        holder.tv_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });



        holder.tv_price.setText("$ "+String.valueOf(product.getPrice()));
        holder.tv_desc.setText(product.getProductDescription());

        holder.img_content.mHeight = (int) product.getImage().getHeight();
        holder.img_content.mWidth = (int) product.getImage().getWidth();

        Picasso.with(mAppContext).load(product.getImage().getUrl()).into(holder.img_content);
        return view;
    }

    class Holder {
        STGVImageView img_content;
        TextView tv_price;
        TextView tv_desc;
    }

}
