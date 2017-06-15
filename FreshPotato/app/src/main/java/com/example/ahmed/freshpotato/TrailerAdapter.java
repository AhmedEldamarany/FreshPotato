package com.example.ahmed.freshpotato;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TrailerAdapter extends BaseAdapter {
    Context context;
    ArrayList<Trailer> trailers;
    public TrailerAdapter(Context context, ArrayList<Trailer> trailers) {
        this.context=context;
        this.trailers=trailers;
    }

    @Override
    public int getCount() {
        return trailers.size();
    }

    @Override
    public Object getItem(int position) {
        return trailers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {


        if (view == null) {

            view=LayoutInflater.from(context).inflate(R.layout.t_r_list_item,viewGroup,false);
        }

        TextView trailerName;

        trailerName = (TextView) view.findViewById(R.id.listText);
        trailerName.setText(trailers.get(position).getName());
        trailerName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity((new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+trailers.get(position).getKey()))));
            }
        });
        return view;
    }
}
