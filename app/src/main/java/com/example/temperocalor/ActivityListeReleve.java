package com.example.temperocalor;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ActivityListeReleve extends Activity {

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_releve);
        Button Valider = findViewById(R.id.buttonValider2);
        Button Retour = findViewById(R.id.buttonRetour2);
        final DAOBdd Bdd = new DAOBdd(this);
        Bdd.open();
        //recup des éléments du layout
        final String[] unLac = new String[1];
        final CalendarView date = findViewById(R.id.calendrierListe);
        final TextView DateText = findViewById(R.id.textDate);
        final TextView Temp6 = findViewById(R.id.temp6);
        final TextView Temp12 = findViewById(R.id.temp12);
        final TextView Temp18 = findViewById(R.id.temp18);
        final TextView Temp00 = findViewById(R.id.temp00);
        final Spinner nom = findViewById(R.id.nomLacSpinnerListe);
        final Spinner unité = findViewById(R.id.unitéSpinner);
        List lesLacs = Bdd.getAllNomLac();
        String Test = lesLacs.get(1).toString();
        //Toast.makeText(getApplicationContext(), Bdd.getTemp6Releve("16-11-2020", "1"), Toast.LENGTH_LONG).show();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesLacs);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nom.setAdapter(arrayAdapter);
        nom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unLac[0] = String.valueOf(nom.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });
        date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String Date
                        = dayOfMonth + "-"
                        + (month + 1) + "-" + year;

                // set this date in TextView for Display
                DateText.setText(Date);

            }
        });

        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            public void onClick(View v) {
                Intent intent = new Intent(ActivityListeReleve.this, MainActivity.class);
                startActivity(intent);

                switch (v.getId()) {
                    case R.id.buttonValider2:
                        String Nlac = nom.getSelectedItem().toString();
                        final String IdLac = Bdd.getIdFromNom(Nlac);
                        String Date = DateText.getText().toString();
                        String Temp6t = Bdd.getTemp6Releve(Date, IdLac);
                        String Temp12t = Bdd.getTemp12Releve(Date, IdLac);
                        String Temp18t = Bdd.getTemp18Releve(Date, IdLac);
                        String Temp00t = Bdd.getTemp00Releve(Date, IdLac);
                        //Toast.makeText(getApplicationContext(), IdLac +" "+ Date, Toast.LENGTH_LONG).show();
                        //Toast.makeText(getApplicationContext(), Bdd.getTempReleve(Date, IdLac).toString(), Toast.LENGTH_LONG).show();
                       Temp6.setText(Temp6t);
                       Temp12.setText(Temp12t);
                       Temp18.setText(Temp18t);
                       Temp00.setText(Temp00t);
                        break;

                    case R.id.buttonRetour2:
                        finish();
                        break;}
            }
        };
        Valider.setOnClickListener(ecouteur1);
        Retour.setOnClickListener(ecouteur1);
    }
}
