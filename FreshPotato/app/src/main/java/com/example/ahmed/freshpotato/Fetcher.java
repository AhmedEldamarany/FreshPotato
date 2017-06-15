package com.example.ahmed.freshpotato;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
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

/**
 * Created by Ahmed on 12/4/2016.
 */




public  class Fetcher extends AsyncTask<String, String[], Boolean> {
    TrailerAdapter trailerAdapter;
    ReviewAdapter reviewAdapter;
    int id;
    ArrayList<Review> AllRvevies;
    ArrayList<Trailer> AllTrailers;
Context context;
    ListView lister;

    public Fetcher(int id, Context context, ListView lister) {
        this.id=id;
        this.lister=lister;
        this.context=context;
        if (id==1) this.AllRvevies =new ArrayList<Review>();
        else if(id==2) this.AllTrailers=new ArrayList<Trailer>();
        else Toast.makeText(context,"Error in Parsing Trailers And Reviews",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(Boolean bool) {
        if(bool&&this.context!=null){

                    if(id==1&&!(AllRvevies.isEmpty())){
                         reviewAdapter=new ReviewAdapter(this.context,AllRvevies);
                lister.setAdapter(reviewAdapter);
                }


            else if(id==2&&!(AllTrailers.isEmpty())) {
                        trailerAdapter = new TrailerAdapter(this.context, AllTrailers);
                        lister.setAdapter(trailerAdapter);
                    }
        }

    }

    @Override
    protected Boolean doInBackground(String... params) {

        if (params.length == 0) return false;
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
                Toast.makeText(context,"There's No Connection",Toast.LENGTH_SHORT).show();



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
                    Log.e("Error", "Error closing stream", e);
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

    private Boolean getDataFromJson(String JsonStr) throws JSONException {
        if(this.id==1){// we would get the Reviews

            try{

                JSONObject elkol = new JSONObject(JsonStr);
                JSONArray result =  elkol.getJSONArray("results");
                for(int i=0; i<result.length();i++){
                    JSONObject reviewJSON=result.getJSONObject(i);
                    Review reviewObject=new Review();
                    reviewObject.setAuthor(reviewJSON.getString("author"));
                    reviewObject.setContent(reviewJSON.getString("content"));
                     AllRvevies.add(reviewObject);
                }
                return true;
            }
            catch (JSONException e ){return false;}
        }
        else {
            try{

                JSONObject elkol = new JSONObject(JsonStr);
                JSONArray result =  elkol.getJSONArray("results");

                for(int i=0; i<result.length();i++){
                    JSONObject trailerJSON=result.getJSONObject(i);
                    Trailer trailerObject=new Trailer();
                    if (trailerJSON.getString("site").equals("YouTube")) {

                        trailerObject.setKey(trailerJSON.getString("key"));
                    trailerObject.setSite(trailerJSON.getString("site"));
                    trailerObject.setName(trailerJSON.getString("name"));
                     AllTrailers.add(trailerObject);
                }
            }
                return true;
            }
            catch (JSONException e ){return false;}


        }
    }


}
