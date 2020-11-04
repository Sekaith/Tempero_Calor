package com.example.temperocalor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
}