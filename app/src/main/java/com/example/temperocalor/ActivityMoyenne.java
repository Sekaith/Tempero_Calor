package com.example.temperocalor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class ActivityMoyenne extends Activity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moyenne);
        Button Retour = findViewById(R.id.buttonRetour3);
        Button Valider = findViewById(R.id.buttonVal);
        final DAOBdd Bdd = new DAOBdd(this);
        Bdd.open();
        final String[] unLac = new String[1];
        final RadioButton heure6 = findViewById(R.id.radioButton_heure_6);
        final RadioButton heure12 = findViewById(R.id.radioButton_heure_12);
        final RadioButton heure18 = findViewById(R.id.radioButton_heure_18);
        final RadioButton heure00 = findViewById(R.id.radioButton_heure_00);
        final Spinner Mois = findViewById(R.id.MoisSpinner);
        final Spinner Lacs = findViewById(R.id.nomLacSpinnerListe2);
        final Spinner Unité = findViewById(R.id.unitéSpinner);
        final TextView Moyenne = findViewById(R.id.Moyenne_Temp);
        final TextView CX = findViewById(R.id.CoordonnéeX);
        final TextView CY = findViewById(R.id.CoordonnéeY);

        List lesLacs = Bdd.getAllNomLac();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, lesLacs);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Lacs.setAdapter(arrayAdapter);
        Lacs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unLac[0] = String.valueOf(Lacs.getSelectedItem());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView){

            }
        });





        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityMoyenne.this, MainActivity.class);
                startActivity(intent);
                switch (v.getId()) {
                    case R.id.buttonVal:
                        String MoisE = Mois.getSelectedItem().toString();
                        String Nlac = Lacs.getSelectedItem().toString();
                        final String IdLac = Bdd.getIdFromNom(Nlac);
                        CX.setText(Bdd.getCoordXFromNom(Nlac));
                        CY.setText(Bdd.getCoordYFromNom(Nlac));
                        if (heure6.isChecked()){
                            int moy = Bdd.getMoyAllReleveFromLac6(IdLac, MoisE);
                            if (Unité.getSelectedItem().toString().equals("Celsius")){
                                String moyy = String.valueOf(moy);
                                Moyenne.setText(moyy);
                            }
                            else if (Unité.getSelectedItem().toString().equals("Fahrenheit")){
                                moy = moy+32;
                                String moyy = String.valueOf(moy);
                                Moyenne.setText(moyy);
                            }
                        }else if(heure12.isChecked()){
                            int moy = Bdd.getMoyAllReleveFromLac6(IdLac, MoisE);
                            if (Unité.getSelectedItem().toString().equals("Celsius")){
                                String moyy = String.valueOf(moy);
                                Moyenne.setText(moyy);
                            }
                            else if (Unité.getSelectedItem().toString().equals("Fahrenheit")){
                                moy = moy+32;
                                String moyy = String.valueOf(moy);
                                Moyenne.setText(moyy);
                            }
                        }else if(heure18.isChecked()){
                            int moy = Bdd.getMoyAllReleveFromLac6(IdLac, MoisE);
                            if (Unité.getSelectedItem().toString().equals("Celsius")){
                                String moyy = String.valueOf(moy);
                                Moyenne.setText(moyy);
                            }
                            else if (Unité.getSelectedItem().toString().equals("Fahrenheit")){
                                moy = moy+32;
                                String moyy = String.valueOf(moy);
                                Moyenne.setText(moyy);
                            }
                        }else if(heure00.isChecked()){
                            int moy = Bdd.getMoyAllReleveFromLac6(IdLac, MoisE);
                            if (Unité.getSelectedItem().toString().equals("Celsius")){
                                String moyy = String.valueOf(moy);
                                Moyenne.setText(moyy);
                            }
                            else if (Unité.getSelectedItem().toString().equals("Fahrenheit")){
                                moy = moy+32;
                                String moyy = String.valueOf(moy);
                                Moyenne.setText(moyy);
                            }
                        }

                        break;

                    case R.id.buttonRetour3:

                        break;}

            }

        };
        Valider.setOnClickListener(ecouteur1);
        Retour.setOnClickListener(ecouteur1);
    }
}
