package com.example.ahmed.freshpotato;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class FragPosters extends Fragment {

    GridView gridder;
    private final String LOG_TAG = FragPosters.class.getSimpleName();
    static SensetiveData Secrets=new SensetiveData();// cause the url is static
    private static String url=Secrets.getMostPOP(); // Must be static to be change at any time the onCreate is called
    public static int id_to_Posters=1;
    List<Aflam> elAflam= new ArrayList<Aflam>();
    ArrayList<Aflam> Favourites=new ArrayList<Aflam>();

    SQLiteDatabase sqLiteDatabase;
    PreferedDB preferedDB;
    Cursor cursor;
    public FragPosters() {

    }
// Not to Crash when there is no Internet Connection
    boolean internet_connection(){
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.sorter,menu);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);


    }
    public void Display(){
        switch (this.id_to_Posters){
        case 1:
            this.url=Secrets.getTopRated();
              break;

            case 2:
                this.url=Secrets.getMostPOP();
                break;

            case 3:
                DisplayFavourite();
    break;
        }


    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        if(id==R.id.Favourite)
        {
            this.id_to_Posters=3;
        Display();
        }
        else { if(id==R.id.top_rated)
            this.id_to_Posters=1;
            else if (id==R.id.popular)
            this.id_to_Posters=2;
            Display();
                if (this.internet_connection()) {
                    Fetch fetcher = new Fetch();
                    fetcher.execute(this.url);
                }
                return true;


        }
        return super.onOptionsItemSelected(item);
    }
    public void DisplayFavourite()throws NullPointerException
    {
        preferedDB =new PreferedDB(getContext());
        sqLiteDatabase=preferedDB.getReadableDatabase();
        cursor=preferedDB.GET(sqLiteDatabase);
  Favourites.clear();
        ArrayList<String> Posters=new ArrayList<String>();
        String BaseURl = "http://image.tmdb.org/t/p/w154/";

        if(cursor.moveToFirst()) // if the data base has elements
        {
            do {
                Aflam film2dem=new Aflam();

                    Posters.add(BaseURl.concat(cursor.getString(0)));
                film2dem.setPoster(cursor.getString(0));
                film2dem.setTilte(cursor.getString(1));
                film2dem.setPhoto(cursor.getString(2));
                film2dem.setNobza(cursor.getString(3));
                film2dem.setVoteAvg(cursor.getString(4));
                film2dem.setRelease_date(cursor.getString(5));
                film2dem.setId(cursor.getString(6));
                    Favourites.add(film2dem);
             }
            while (cursor.moveToNext());// if ther's more movies

            gridder.setAdapter(new ImageAdapter(getContext(),Posters));
            gridder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Aflam filme = Favourites.get(position); 
                    ((Callback)getActivity()).onItemSelected(filme);

                }

            });
        }
        else
            Toast.makeText(getContext(),"You haven't added any ",Toast.LENGTH_LONG).show();






    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View rootView = inflater.inflate(R.layout.fragment_frag_posters, container, false);
         gridder = (GridView) rootView.findViewById(R.id.gridv);

   Display();
   if(this.id_to_Posters!=3){//OnRotate the favorite will still displaying the favorites

if(this.internet_connection()) {
    Fetch fetcher = new Fetch();
    fetcher.execute(this.url);
}}

        return rootView;
    }


    //////////////////////////THE INNER CLASS////////////////////////
    //////////////////////////THE INNER CLASS////////////////////////
    //////////////////////////THE INNER CLASS////////////////////////
    //////////////////////////THE INNER CLASS////////////////////////

    public  class Fetch extends AsyncTask<String, String[], ArrayList<String>> {


        public Fetch() {

        }

        @Override
        protected void onPostExecute(ArrayList<String> Image) {

            gridder.setAdapter(new ImageAdapter(getContext(),Image));
            gridder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override


                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Aflam filme = elAflam.get(position);

                    ((Callback)getActivity()).onItemSelected(filme);


                }
            });
        }

        @Override
        protected ArrayList<String> doInBackground(String... params) {

            if (params.length == 0) return null;
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            String JsonStr = null;
            try {
URL urll =new URL(params[0]);

                urlConnection = (HttpURLConnection) urll.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {

                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                JsonStr = buffer.toString();
                if(JsonStr==null)
                    Toast.makeText(getContext(),"There's No Connection",Toast.LENGTH_SHORT).show();



            } catch (IOException e) {

                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }

            try {
                return getDataFromJson(JsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            return null;
        }

        private ArrayList getDataFromJson(String JsonStr) throws JSONException {
            ArrayList<String> Sowar=new ArrayList<String>();
String results= "results";
            String BaseURl = "http://image.tmdb.org/t/p/w154/";
            try {
                JSONObject elkol = new JSONObject(JsonStr);
                JSONArray result =  elkol.getJSONArray(results);
                elAflam.clear();
                for(int i=0; i<result.length();i++){
                    JSONObject Film= result.getJSONObject(i);
                    String poster= Film.getString("poster_path");
                    Sowar.add( BaseURl.concat(poster ));

                    Aflam filmGded=new Aflam();

                        filmGded.setPoster(Film.getString("poster_path"));
                        filmGded.setNobza(Film.getString("overview"));
                        filmGded.setPhoto(Film.getString("backdrop_path"));
                        filmGded.setTilte(Film.getString("title"));
                        filmGded.setVoteAvg(Film.getString("vote_average"));
                        filmGded.setRelease_date(Film.getString("release_date"));
                        filmGded.setId(""+Film.getInt("id"));
                    if(elAflam!=null)
                        elAflam.add(filmGded);


                }}
            catch (JSONException e ){}



            return Sowar  ;

        }


    }

    public interface Callback {
        /**
         * DetailFragmentCallback for when an item has been selected.
         */
        public void onItemSelected(Aflam filme);
    }

}