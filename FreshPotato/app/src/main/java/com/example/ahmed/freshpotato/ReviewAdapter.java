package com.example.ahmed.freshpotato;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class ReviewAdapter extends BaseAdapter{
    Context context;
ArrayList<Review> reviews;
    public ReviewAdapter(Context context, ArrayList<Review> reviews) {

        this.context=context;
        this.reviews=reviews;
    }

    @Override
    public int getCount() {
        return reviews.size() ;
    }

    @Override
    public Object getItem(int position) {
        return reviews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

@Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        View row = convertView;
        if (row == null) {
            row = LayoutInflater.from(this.context).inflate(R.layout.t_r_list_item,
                    viewGroup, false);
        }
        TextView reviewAuthor = (TextView) row.findViewById(R.id.listText);
        reviewAuthor.setText(reviews.get(position).getAuthor().concat("/n"+reviews.get(position).getContent()));

        return row;
    }    
    }
