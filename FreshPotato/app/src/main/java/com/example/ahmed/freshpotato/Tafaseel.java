package com.example.ahmed.freshpotato;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


/**
 * Created by Ahmed on 11/19/2016.
 */

public class Tafaseel extends AppCompatActivity {
public Tafaseel (){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {

            Bundle args =new Bundle();

            args.putParcelable("TheFilm",getIntent().getParcelableExtra("movie"));
            Tafaseel.Dataa frage=new Tafaseel.Dataa();
            frage.setArguments(args);

            getSupportFragmentManager().beginTransaction().replace(R.id.details_active,frage).commit();

        }

    }



////////////////////INNER CLASSE/////////////////


    public static class Dataa extends Fragment  {
        public Dataa(){}
        PreferedDB preferedDB;
        SQLiteDatabase sqLiteDatabase;
Aflam favourite;
        public SensetiveData UrLProvider=new SensetiveData();

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }


        public Boolean isFavorite() {

            Cursor cursor = null;
            if(favourite!=null){
                try{

    preferedDB = new PreferedDB(getContext());
    sqLiteDatabase = preferedDB.getWritableDatabase();
    cursor = sqLiteDatabase.rawQuery("SELECT idd FROM favouritMovie WHERE idd=?", new String[]{this.favourite.getId() + ""});

                if(cursor.getCount() > 0)
                return true;
            }
            finally {
if(cursor!=null)
                cursor.close();
            }}
            return false;
        }
public void AddFavorite(){

            try {
                preferedDB = new PreferedDB(getContext());
                sqLiteDatabase = preferedDB.getWritableDatabase();
                if(isFavorite())
                    Toast.makeText(getContext(), favourite.getTilte() + " is one of your Favorites", Toast.LENGTH_LONG).show();
else
                {
                preferedDB.ADD(this.favourite, sqLiteDatabase);

                Toast.makeText(getContext(), favourite.getTilte() + " has been added to favourites", Toast.LENGTH_LONG).show();
            }} catch (NullPointerException e) {// if favorite is null
                Toast.makeText(getContext(), "Not Added", Toast.LENGTH_LONG).show();
            }
            preferedDB.close();
        }
        public void DelFavorite(){

            preferedDB = new PreferedDB(getContext());
            sqLiteDatabase = preferedDB.getWritableDatabase();
String[] whereArg=new String[]{this.favourite.getId()};
            sqLiteDatabase.delete(PreferedFilm.PrederdDetails.TABLENAME,"idd=?",whereArg);

        }





        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.frag_detail, container, false);
            ListView Rlister=(ListView) rootView.findViewById(R.id.Rlist);
            ListView Tlister=(ListView) rootView.findViewById(R.id.Tlist);
            Switch switcher=(Switch) rootView.findViewById(R.id.switcher);

            switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) AddFavorite();
                    else DelFavorite();
                }});

            Bundle args = getArguments();
            if (args != null)
                favourite = args.getParcelable("TheFilm");



            String BaseURl = "http://image.tmdb.org/t/p/w154/";
            ImageView backdrop =(ImageView) rootView.findViewById(R.id.backdrop);
try {

    Picasso.with(getContext()).load(BaseURl.concat(favourite.getPhoto())).into(backdrop);

    TextView Title = (TextView) rootView.findViewById(R.id.TheTitle);
    Title.setText(favourite.getTilte());
    TextView Nobza = (TextView) rootView.findViewById(R.id.nabza);
    Nobza.setText(favourite.getNobza());
    TextView Rater = (TextView) rootView.findViewById(R.id.rate);
    Rater.setText("Rated   "+favourite.getVoteAvg());
    TextView dater = (TextView) rootView.findViewById(R.id.date);
    dater.setText(favourite.getRelease_date());
    Fetcher fetcher =new Fetcher(1,getContext(),Rlister);
    fetcher.execute(UrLProvider.getReview(favourite.getId()));
    Fetcher fetcher2 =new Fetcher(2,getContext(),Tlister);
    fetcher2.execute(UrLProvider.getTrailr(favourite.getId()));


}
catch (NullPointerException e){}



           if(isFavorite()) switcher.setChecked(true);

            return rootView;

        }

    }



}
