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
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, lesLacs);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nom.setAdapter(arrayAdapter);
        nom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                unLac[0] = String.valueOf(nom.getSelectedItem());
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
                            if(heure6.isChecked()){

                                String Nlac = nom.getSelectedItem().toString();
                                String IdLac = Bdd.getIdFromNom(Nlac);
                                Releve releve = new Releve(null,DateText.toString(), ReleveTemp.toString(),null,null,null,IdLac);
                                Bdd.insererReleve(releve);
                                Bdd.close();
                                finish();
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







