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

    /*public void remplirTableLac() {
        DAOBdd lacBdd = new DAOLac(this);
        Lac lac1 = new Lac ( "L001", "Lac de Grand-Lieu", 47.095212, -1.673092);
        Lac lac2 = new Lac ( "L002", "Lac de l'Espérance",46.779756,-0.853628 );
        Lac lac3 = new Lac ( "L003", "Lac de la Vallée Mabille", 47.356276, -1.924115 );

        //on ouvre la base de données
        lacBdd.open();

        //on insère chaque lac un par un
        lacBdd.insererLac(lac1);
        lacBdd.insererLac(lac2);
        lacBdd.insererLac(lac3);

        //le curseur pour afficher le nombre de lacs dans la base
        Cursor c = lacBdd.getData();
        Toast.makeText(getApplicationContext(), " il y a " + String.valueOf(c.getCount()) + " lacs ", Toast.LENGTH_LONG).show();
    }*/
}