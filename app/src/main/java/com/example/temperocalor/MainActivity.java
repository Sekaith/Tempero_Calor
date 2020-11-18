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
        //viderTableR();
        //viderTableL();
        //remplirTable();
        final DAOBdd Bdd = new DAOBdd(this);
        Bdd.open();
        //Toast.makeText(getApplicationContext(), Bdd.getTemp6Releve("15-11-2020", "1").toString(), Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(), Bdd.getAllLac().toString(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), "V02", Toast.LENGTH_LONG).show();
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
        lacBDD.open();
        Lac lac1 = new Lac( "Lac01", 43.771450, 6.189804);
        Lac lac2 = new Lac("Lac02", 45.729632, 5.869561);
        Lac lac3 = new Lac("Lac03", 15.729632, 89.869561);



        lacBDD.insererLac(lac1);
        lacBDD.insererLac(lac2);
        lacBDD.insererLac(lac3);
        Cursor c1 = lacBDD.getDataReleve();

    }

    public void viderTableL() {
        DAOBdd lacBDD = new DAOBdd(this);
        lacBDD.open();
        lacBDD.dropLac();
        lacBDD.dropRel();
    }
    public void viderTableR() {
        DAOBdd relBDD = new DAOBdd(this);
        relBDD.open();
        relBDD.dropRel();
    }
}