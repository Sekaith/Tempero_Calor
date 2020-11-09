package com.example.temperocalor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        remplirTable();
        setContentView(R.layout.activity_main);
        //Accès au menu pour saisir un relevé
        Button saisirReleve = findViewById(R.id.buttonSaisieReleve);
        View.OnClickListener ecouteur1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActivitySaisirReleve.class);
                startActivity(intent);
            }
        };
        saisirReleve.setOnClickListener(ecouteur1);

        Button listeReleve = findViewById(R.id.buttonListeReleve);
        View.OnClickListener ecouteur2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, ActivityListeReleve.class);
                startActivity(intent2);
            }
        };
        listeReleve.setOnClickListener(ecouteur2);

        Button listeMoyenne = findViewById(R.id.buttonMoyenne);
        View.OnClickListener ecouteur3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, ActivityMoyenne.class);
                startActivity(intent3);
            }
        };
        listeMoyenne.setOnClickListener(ecouteur3);
    }
    public void remplirTable () {
        DAOBdd lacBDD = new DAOBdd(this);
        Lac lac1 = new Lac("01", "Sainte-Croix", 43.771450, 6.189804);
        Lac lac2 = new Lac("02", "Bourget", 45.729632 , 5.869561);
        lacBDD.open();
        lacBDD.insererLac(lac1);
        lacBDD.insererLac(lac2);
        Cursor c = lacBDD.getDataLac();
        Toast.makeText(getApplicationContext(), " il y a " + String.valueOf(c.getCount()) + " lacs ", Toast.LENGTH_LONG).show();
    }
}