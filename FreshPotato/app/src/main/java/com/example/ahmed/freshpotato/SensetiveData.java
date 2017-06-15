package com.example.ahmed.freshpotato;

/**
 * Created by Ahmed on 12/4/2016.
 */

public class SensetiveData {
    private String APIKey="?api_key=79ae98b3168e6bf2a41782bcbe9219e9";
    private String URL="http://api.themoviedb.org/3/movie/";


    public String getTrailr(String id)
    {
       return this.URL.concat(id+"/videos").concat(APIKey);
    }

    public String getMostPOP() {
        return URL.concat("popular").concat(APIKey);
    }

    public String getTopRated() {
        return URL.concat("top_rated").concat(APIKey);
    }
   public String getReview(String id){
       return URL.concat(id+"/reviews").concat(APIKey);
   }

    public String getURL() {
        return URL;
    }

    public String getAPIKey() {
        return APIKey;
    }
}
