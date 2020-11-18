package com.example.temperocalor;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ActivitySaisirReleve extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisir_releve);
        //gestion des boutons enregistrer et retour
        Button Enregistrer = findViewById(R.id.buttonEnregistrer);
        Button Retour = findViewById(R.id.buttonRetour);
        final DAOBdd Bdd = new DAOBdd(this);
        Bdd.open();

        //récupération des données saisies
        final String[] unLac = new String[1];
        final CalendarView date = findViewById(R.id.calendrierSaisie);
        final TextView DateText = findViewById(R.id.DateText);
        final RadioButton heure6 = findViewById(R.id.radioButton_heure_6);
        final RadioButton heure12 = findViewById(R.id.radioButton_heure_12);
        final RadioButton heure18 = findViewById(R.id.radioButton_heure_18);
        final RadioButton heure00 = findViewById(R.id.radioButton_heure_00);
        final Spinner nom = findViewById(R.id.nomLacSpinner);
        final EditText ReleveTemp = findViewById(R.id.editTextReleveTemp);
        List lesLacs = Bdd.getAllNomLac();
        String Test = lesLacs.get(1).toString();
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

            //on va créer un écouteur pour un groupe de boutons
            View.OnClickListener ecouteur1 = new View.OnClickListener() {
                //on implémente la méthode onclick
                public void onClick(View v) {
                    Intent intent = new Intent(ActivitySaisirReleve.this, MainActivity.class);
                    startActivity(intent);

                    switch (v.getId()) {
                        case R.id.buttonEnregistrer:
                            //Toast.makeText(getApplicationContext(), DateText.getText().toString(), Toast.LENGTH_LONG).show();
                            String Nlac = nom.getSelectedItem().toString();
                            String IdLac = Bdd.getIdFromNom(Nlac);
                            String Date = DateText.getText().toString();
                            Releve releve = new Releve(Date, "","","","",IdLac);
                            Bdd.insererReleve(releve);
                            if(heure6.isChecked()){

                                String Temp = ReleveTemp.getText().toString();
                                Bdd.InsertTemp6(Date,IdLac,Temp);
                                //Toast.makeText(getApplicationContext(), Temp, Toast.LENGTH_LONG).show();
                                Bdd.close();

                                break;
                            }
                            if(heure12.isChecked()){

                                String Temp = ReleveTemp.getText().toString();
                                Bdd.InsertTemp12(Date,IdLac,Temp);
                                //Toast.makeText(getApplicationContext(), Temp, Toast.LENGTH_LONG).show();
                                Bdd.close();

                                break;
                            }
                            if(heure18.isChecked()){

                                String Temp = ReleveTemp.getText().toString();
                                Bdd.InsertTemp18(Date,IdLac,Temp);
                                //Toast.makeText(getApplicationContext(), Temp, Toast.LENGTH_LONG).show();
                                Bdd.close();

                                break;
                            }
                            if(heure00.isChecked()){

                                String Temp = ReleveTemp.getText().toString();
                                Bdd.InsertTemp00(Date,IdLac,Temp);
                                //Toast.makeText(getApplicationContext(), Temp, Toast.LENGTH_LONG).show();
                                Bdd.close();

                                break;
                            }
                        case R.id.buttonRetour:
                            finish();
                            break;}
                }
            };
        Enregistrer.setOnClickListener(ecouteur1);
        Retour.setOnClickListener(ecouteur1);
        }
}







