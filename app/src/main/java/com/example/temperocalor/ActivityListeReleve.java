package com.example.temperocalor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityListeReleve extends Activity {
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_releve);
        Button Retour = findViewById(R.id.buttonRetour2);
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
