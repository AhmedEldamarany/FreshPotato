package com.example.ahmed.freshpotato;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed on 11/2/2016.
 */

public class ImageAdapter extends BaseAdapter {
    Context contexter;
    ArrayList<String> Posters=new ArrayList<String>();

    public ImageAdapter(Context context,ArrayList<String> Poster) {

        contexter=context;
    this.Posters=Poster;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount()  {

        return this.Posters.size();
    }

    @Override
    public Object getItem(int position) {
        return Posters.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView poster=(ImageView) convertView;
        if (poster == null) {
            poster = new ImageView(contexter);
            poster.setLayoutParams(new GridView.LayoutParams(250, 400));
            poster.setScaleType(ImageView.ScaleType.CENTER_CROP);
            poster.setPadding(0, 0, 0, 0);
        }
        else {
        poster = (ImageView) convertView;
    }

    Picasso.with(contexter).load(Posters.get(position)).into(poster);

    return poster;

    }
}
