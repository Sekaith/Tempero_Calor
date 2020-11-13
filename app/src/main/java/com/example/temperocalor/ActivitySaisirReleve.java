package com.example.temperocalor;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;



import com.google.android.gms.common.api.Api;

import java.util.ArrayList;

public class ActivitySaisirReleve extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saisir_releve);
        //gestion des boutons enregistrer et retour
        Button Enregistrer = findViewById(R.id.buttonEnregistrer);
        Button Retour = findViewById(R.id.buttonRetour);
        final DAOBdd lacBdd = new DAOBdd(this);
        lacBdd.open();

        //récupération des données saisies
        final CalendarView date = findViewById(R.id.calendrierSaisie);
        final RadioButton heure6 = findViewById(R.id.radioButton_heure_6);
        final RadioButton heure12 = findViewById(R.id.radioButton_heure_12);
        final RadioButton heure18 = findViewById(R.id.radioButton_heure_18);
        final RadioButton heure00 = findViewById(R.id.radioButton_heure_00);
        final Spinner nom = findViewById(R.id.nomLacSpinner);
        final EditText personne = findViewById(R.id.editTextTextPersonName2);
        ArrayList<String> listeLac = new ArrayList<>();
        listeLac.add("Lac1");
        listeLac.add("Lac2");
        listeLac.add("Lac3");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listeLac);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nom.setAdapter(arrayAdapter);
        //on va créer un écouteur pour un groupe de boutons
        /*View.OnClickListener ecouteur1 = new View.OnClickListener() {
            //on implémente la méthode onclick
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySaisirReleve.this, MainActivity.class);
                startActivity(intent);

                switch (v.getId()) {
                    case R.id.buttonEnregistrer:
                        Lac lac = new Lac(date.getCalendar().toString(), heure6.getRadioButton().toString(), heure12.getRadioButton().toString(), heure18.getRadioButton().toString(), heure00.getRadioButton().toString(), nom.getSelectedItem().toString(), personne.getText().toString());
                        lacBdd.insererLac(lac);
                        lacBdd.close();
                        finish();
                        break;
                    case R.id.buttonRetour:
                        finish();
                        break;
                }
            }
        };

        Enregistrer.setOnClickListener(ecouteur1);
        Retour.setOnClickListener(ecouteur1);
*/
    }

}
