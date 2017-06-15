package com.example.ahmed.thecompletegpa;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private int Hours=0;
    private float Grades=0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Grades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        final EditText Grades=(EditText) findViewById(R.id.editTextGrades);
        Grades.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {

               EditText Gpa= (EditText) findViewById(R.id.editTextGPA);
                try{
                    Gpa.setText(getPoints(Integer.parseInt(Grades.getText().toString())) + "");
                }
                catch (NumberFormatException e){


                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {}
        });


        Spinner spinner1=(Spinner) findViewById(R.id.spinner);
                spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                EditText Gpa = (EditText) findViewById(R.id.editTextGPA);
String x=parent.getItemAtPosition(position).toString().trim();
                float y=getPoints(x);
                Gpa.setText(getPoints(x) + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    public void Add(View view){
        try {
            EditText Grades = (EditText) findViewById(R.id.editTextGPA);
            EditText Hours = (EditText) findViewById(R.id.editTextHours);
            this.Hours += Integer.parseInt(Hours.getText().toString());
            this.Grades += Float.parseFloat(Grades.getText().toString())
                    * Float.parseFloat(Hours.getText().toString());
            Hours.setText("");
            EditText Points = (EditText) findViewById(R.id.editTextGrades);
            Points.setText("");
        }
        catch (NumberFormatException e )
        {
            Toast.makeText(this,"You Should fill The Hours Field First",Toast.LENGTH_SHORT);
            Log.e("Tag", "this is shit",e);
            //this is shit
        }

    }
    public void Calc(View view){
        Add(view);
        Float GPA= Float.valueOf(Grades/Hours);
TextView textview=(TextView) findViewById(R.id.fool);
        textview.setText("Your GPA is "+GPA +" AlHamdulillah ");



    }
    private Float getPoints(int grades){
        if(grades<50)
            return 0f;
        else if(grades<53)
            return 1f;
        else if(grades<56)
            return 1.33f;
        else if(grades<60)
            return 1.67f;
        else if(grades<65)
            return 2f;
        else if(grades<70)
            return 2.33f;
        else if(grades<75)
            return 2.67f;
        else if(grades<80)
            return 3f;
        else if(grades<85)
            return 3.33f;
        else if(grades<90)
            return 3.67f;
        else
            return 4f;

    }
    private Float getPoints(String grades){

        if(grades.equals("F"))
            return 0.0f;
        else if(grades.equals("D"))
            return 1f;
        else if(grades.equals("D+"))
            return 1.33f;
        else if(grades.equals("C-"))
            return 1.67f;
        else if(grades.equals("C"))
            return 2f;
        else if(grades.equals("C+"))
            return 2.33f;
        else if(grades.equals("B-"))
            return 2.67f;
        else if(grades.equals("B"))
            return 3f;
        else if(grades.equals("B+"))
            return 3.33f;
        else if(grades.equals("A-"))
            return 3.67f;
        else if(grades.equals("A"))
            return 4f;
        else return 50f;

    }

    }
