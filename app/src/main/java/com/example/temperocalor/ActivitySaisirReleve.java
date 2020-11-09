package com.example.temperocalor;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.List;

public class ActivitySaisirReleve extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        //Liste deroulante des noms des lacs
        final Spinner spinnerNomLacs = (Spinner) findViewById(R.id.nomLacSpinner);
        DAOBdd daobdd = new DAOBdd(this);
        daobdd.open();

        final String[] lesLacs = new String[0];
        List nomLacs = daobdd.getAllNomLac();
        daobdd.close();

        ArrayAdapter<String> dataAdaptaterLac = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nomLacs);
        dataAdaptaterLac.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNomLacs.setAdapter(dataAdaptaterLac);
        spinnerNomLacs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent , View view, int position, long id) {
                lesLacs[0] = String.valueOf(spinnerNomLacs.getSelectedItem());

            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterview) {

            }
        });


        setContentView(R.layout.activity_saisir_releve);
        Button Retour = findViewById(R.id.buttonRetour);
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivitySaisirReleve.this, MainActivity.class);
                startActivity(intent);
            }
        };
        Retour.setOnClickListener(ecouteur1);
    }

}
