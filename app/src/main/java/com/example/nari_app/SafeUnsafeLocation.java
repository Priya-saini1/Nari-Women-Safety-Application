package com.example.nari_app;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class SafeUnsafeLocation extends AppCompatActivity {
    String place, nameOfPlace;
    Spinner spinner, spinner2;
    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safe_unsafe_location);
        spinner = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        txtInfo = findViewById(R.id.txtInfo);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Police Station");
        arrayList.add("Hospital");
        arrayList.add("Cafe");
        arrayList.add("Educational Institutes");

        ArrayList<String> arrayListForPoliceStation = new ArrayList<>();
        arrayListForPoliceStation.add("B Division police station, Ghogha Circle");
        arrayListForPoliceStation.add("A Division Police Station");
        arrayListForPoliceStation.add("D Division Police Station, RTO");
        arrayListForPoliceStation.add("Mahila Police Station, Nawapara");
        arrayListForPoliceStation.add("Takhteshwar Police Station, Atatbhai Road");

        ArrayList<String> arrayListForHospital = new ArrayList<>();
        arrayListForHospital.add("HCG Hospital, Meghani Circle");
        arrayListForHospital.add("ESIC Hospital, Anandnagar");
        arrayListForHospital.add("Suchak Hospital and Clinic, Kalubha Road");
        arrayListForHospital.add("Sir Takhtasinhji General Hospital, Jail Rd, Kalanala");
        arrayListForHospital.add("BIMS Multispeciality Hospital, Jail Rd");
        arrayListForHospital.add("Pulse Plus Multi Speciality Hospital, Top 3 Circle");

        ArrayList<String> arrayListForCafe = new ArrayList<>();
        arrayListForCafe.add("The Cafe Bistro, Ghogha Road");
        arrayListForCafe.add("The Black And White Cafe, Rupani Road");
        arrayListForCafe.add("The Coffee Cafe, Ghogha Circle Road");
        arrayListForCafe.add("THE UPPERDECK SNOOKER CAFE & GRILL, Waghawadi Road");
        arrayListForCafe.add("Trp4next Cafe-studio, Hill Drive");
        arrayListForCafe.add("Vibes Bistro, AksharWadi Road");

        ArrayList<String> arrayListForEducationalInstitutes = new ArrayList<>();
        arrayListForEducationalInstitutes.add("Mahdi School, Manekwadi");
        arrayListForEducationalInstitutes.add("Sardar Patel Education Institute, Sardar Nagar");
        arrayListForEducationalInstitutes.add("K.R.Doshi Vidhya Sankul, Nr. Rupani Circle");
        arrayListForEducationalInstitutes.add("The KPES College");
        arrayListForEducationalInstitutes.add("GEC Bhavnagar, Vidyanagar");
        arrayListForEducationalInstitutes.add("Samaldas Arts College, Hill Drive");
        arrayListForEducationalInstitutes.add("Naimisharanya School, Sidsar Road");


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        ArrayAdapter<String> arrayAdapterforPoliceStation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListForPoliceStation);
        ArrayAdapter<String> arrayAdapterforHospital = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListForHospital);
        ArrayAdapter<String> arrayAdapterforCafe = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListForCafe);
        ArrayAdapter<String> arrayAdapterforEducation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayListForEducationalInstitutes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapterforPoliceStation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapterforHospital.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapterforCafe.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        arrayAdapterforEducation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                place = adapterView.getItemAtPosition(position).toString();
                if(place=="Police Station"){
                    spinner2.setAdapter(arrayAdapterforPoliceStation);
                } else if (place == "Hospital"){
                    spinner2.setAdapter(arrayAdapterforHospital);
                } else if (place == "Cafe"){
                    spinner2.setAdapter(arrayAdapterforCafe);
                } else {
                    spinner2.setAdapter(arrayAdapterforEducation);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                nameOfPlace = adapterView.getItemAtPosition(position).toString();

                if(position %2 == 0){
                    txtInfo.setText(nameOfPlace + " is Unsafe");
                } else{
                    txtInfo.setText(nameOfPlace + " is Safe");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }
}