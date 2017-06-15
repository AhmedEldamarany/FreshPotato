package com.example.ahmed.freshpotato;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements FragPosters.Callback{
    public boolean TwoPane;
    private static final String DETAILFRAGMENT_TAG = "DFTAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TwoPane = findViewById(R.id.details_active) != null;
        if (TwoPane) {

            if (savedInstanceState == null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.details_active, new Tafaseel.Dataa(), DETAILFRAGMENT_TAG)
                        .commit();
            }
        }
    }





    @Override
    public void onItemSelected(Aflam filme) {
        if(TwoPane){ if(filme!=null){//Tablet Mode
            Bundle args =new Bundle();
            args.putParcelable("TheFilm",filme);
            Tafaseel.Dataa frage=new Tafaseel.Dataa();
            frage.setArguments(args);
            getSupportFragmentManager().beginTransaction().replace(R.id.details_active,frage,DETAILFRAGMENT_TAG).commit();
        }} else //Mobile Phone Mode
        {
            Intent intent = new Intent(this, Tafaseel.class)
                    .putExtra("movie",  filme);
            startActivity(intent);
        }
    }
}
