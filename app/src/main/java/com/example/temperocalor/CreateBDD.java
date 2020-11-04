package com.example.temperocalor;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.util.Date;

public class CreateBDD extends SQLiteOpenHelper {
    private static final String TABLE_LAC = "tlac";
    static final String COL_IDLAC = "_id";
    private static final String COL_NOMLAC = "NomLac";
    private static final String COL_COORD = "Coordonnées";
    private static final String CREATE_TABLELAC = "CREATE TABLE " +
            TABLE_LAC + " ("+COL_IDLAC+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COL_NOMLAC + " TEXT NOT NULL, " + COL_COORD + " TEXT NOT NULL);";
    private static final String TABLE_RELEVE = "treleve";
    static final String COL_IDRELEVE = "_id";
    private static final String COL_JOUR = "Jour";
    private static final String COL_MOIS = "Jour";
    private static final String COL_TEMP6 = "Temp6";
    private static final String COL_TEMP12 = "Temp12";
    private static final String COL_TEMP18 = "Temp18";
    private static final String COL_TEMP24 = "Temp24";
    private static final String CREATE_TABLERELEVE = "CREATE TABLE " +
            TABLE_LAC + " ("+COL_IDRELEVE+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            COL_JOUR + " TEXT NOT NULL, " + COL_MOIS + " TEXT NOT NULL" + COL_TEMP6 + " TEXT NOT NULL" + COL_COORD + " TEXT NOT NULL " + COL_TEMP12 +
            " TEXT NOT NULL" + COL_TEMP18 + " TEXT NOT NULL" + COL_TEMP24 + " TEXT NOT NULL" + COL_IDLAC + " TEXT NOT NULL);";


    public CreateBDD(Context context, String name, SQLiteDatabase.CursorFactory
            factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //appelée lorsqu’aucune base n’existe et que la classe utilitaire doit en créer une
        //on créé la table à partir de la requête écrite dans la variable CREATE_BDD
        db.execSQL(CREATE_TABLELAC);
        db.execSQL(CREATE_TABLERELEVE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //On peut supprimer la table et de la recréer
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LAC + ";");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RELEVE + ";");
        onCreate(db);
    }
}

