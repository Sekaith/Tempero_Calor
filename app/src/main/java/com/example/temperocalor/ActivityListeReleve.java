package com.example.temperocalor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityListeReleve extends Activity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_releve);
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


















        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityListeReleve.this, MainActivity.class);
                startActivity(intent);
            }
        };
        Retour.setOnClickListener(ecouteur1);
    }
}
