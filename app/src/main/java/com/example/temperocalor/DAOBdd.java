package com.example.temperocalor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOBdd {
    static final int VERSION_BDD =1;
    private static final String NOM_BDD = "tempv1.db";
    //table lac
    static final String TABLE_LAC = "tlac";
    static final String COL_IDLAC = "_id";
    static final int NUM_COL_IDLAC = 0;
    static final String COL_COORDX = "Coordonnée_X";
    static final int NUM_COL_COORDX = 1;
    static final String COL_COORDY = "Coordonnée_Y";
    static final int NUM_COL_COORDY = 2;
    static final String COL_NOMLAC = "NomLac";
    static final int NUM_COL_NOMLAC = 3;

    //table releve
    static final String TABLE_RELEVE = "treleve";
    static final String COL_IDRELEVE = "_id";
    static final int NUM_COL_IDRELEVE = 0;
    static final String COL_DATE = "Date";
    static final int NUM_COL_DATE = 1;
    static final String COL_TEMP6 = "Temp6";
    static final int NUM_COL_TEMP6 = 2;
    static final String COL_TEMP12 = "Temp12";
    static final int NUM_COL_TEMP12 = 3;
    static final String COL_TEMP18 = "Temp18";
    static final int NUM_COL_TEMP18 = 4;
    static final String COL_TEMP24 = "Temp24";
    static final int NUM_COL_TEMP24 = 5;
    static final String COL_IDLACR = "IdLac";
    static final int NUM_COL_IDLACR = 6;
    private CreateBDD tableCourante;
    private Context context;
    private SQLiteDatabase db;

    public DAOBdd(Context context){
        this.context = context;
        tableCourante = new CreateBDD(context, NOM_BDD, null, VERSION_BDD);
    }
    public DAOBdd open(){
        db = tableCourante.getWritableDatabase();
        return this;
    }
    public DAOBdd close(){
        db.close();
        return null;
    }
    public long insererLac (Lac unLac){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_IDLAC, unLac.getIdLac());
        values.put(COL_NOMLAC, unLac.getNom());
        values.put(COL_COORDX, unLac.getCoordX());
        values.put(COL_COORDY, unLac.getCoordY());
        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_LAC, null, values);
    }
    public long insererReleve (Releve unReleve){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associé à une clé (qui est le nom de la colonne où on veut mettre la valeur)
        values.put(COL_IDRELEVE, unReleve.getIdReleve());
        values.put(COL_DATE, unReleve.getDate());
        values.put(COL_TEMP6, unReleve.getTemp6h());
        values.put(COL_TEMP12, unReleve.getTemp12h());
        values.put(COL_TEMP18, unReleve.getTemp18h());
        values.put(COL_TEMP24, unReleve.getTemp24h());
        values.put(COL_IDLACR, unReleve.getIdLac());
        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_RELEVE, null, values);
    }
    private Lac cursorToLac(Cursor c){ //Cette méthode permet de convertir un cursor en un client
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon
        c.moveToFirst(); //on se place sur le premier élément
        Lac unLac = new Lac(null,null,0,0 ); //On créé un client
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        unLac.setIdLac(c.getString(NUM_COL_IDLAC));
        unLac.setCoordX(c.getDouble(NUM_COL_COORDX));
        unLac.setCoordY(c.getDouble(NUM_COL_COORDY));
        unLac.setNom(c.getString(NUM_COL_NOMLAC));
        c.close(); //On ferme le cursor
        return unLac; //On retourne le client
    }
    private Releve cursorToReleve(Cursor c){ //Cette méthode permet de convertir un cursor en un relevé
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon
        c.moveToFirst(); //on se place sur le premier élément
        Releve unReleve = new Releve(null,null,null,null, null, null, null); //On créé un client
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        unReleve.setIdReleve(c.getString(NUM_COL_IDRELEVE));
        unReleve.setDate(c.getString(NUM_COL_DATE));
        unReleve.setTemp6h(c.getString(NUM_COL_TEMP6));
        unReleve.setTemp12h(c.getString(NUM_COL_TEMP12));
        unReleve.setTemp18h(c.getString(NUM_COL_TEMP18));
        unReleve.setTemp24h(c.getString(NUM_COL_TEMP24));
        unReleve.setIdLac(c.getString(NUM_COL_IDLACR));
        c.close(); //On ferme le cursor
        return unReleve; //On retourne le client
    }

    public Lac getLacWithNomLac(String nom){
        //Récupère dans un Cursor les valeurs correspondant à un article grâce à sa designation
        Cursor c = db.query(TABLE_LAC, new String[]
                {COL_IDLAC,COL_NOMLAC, COL_COORDX, COL_COORDY, COL_NOMLAC}, COL_NOMLAC + " =\"" + nom +"\"", null, null, null, null);
        return cursorToLac(c);
    }

    public String getIdFromNom(String Nom){

        Cursor c = db.query(TABLE_LAC, new String[]
                {COL_IDLAC}, COL_NOMLAC + " =\"" + Nom +"\"", null, null, null, null);
        return cursorToId(c);

    }
    public String cursorToId(Cursor c){
        c.moveToFirst(); //on se place sur le premier élément
        String Id;
        Id = c.getString(NUM_COL_IDLAC);
        c.close(); //On ferme le cursor
        return Id; //On retourne l'id
    }
    public Releve getReleveWithId(int id){
        //Récupère dans un Cursor les valeurs correspondant à un article grâce à sa designation
        Cursor c = db.query(TABLE_RELEVE, new String[]
                {COL_IDRELEVE,COL_DATE, COL_TEMP6, COL_TEMP12, COL_TEMP18, COL_TEMP24, COL_IDLACR}, COL_IDRELEVE + " =\"" + id +"\"", null, null, null, null);
        return cursorToReleve(c);
    }
    public String cursorToTemp6(Cursor c){
        c.moveToFirst(); //on se place sur le premier élément
        String Temp;
        Temp = c.getString(NUM_COL_TEMP6);
        c.close(); //On ferme le cursor
        return Temp; //On retourne la temperature
    }
    public String cursorToTemp12(Cursor c){
        c.moveToFirst(); //on se place sur le premier élément
        String Temp;
        Temp = c.getString(NUM_COL_TEMP12);
        c.close(); //On ferme le cursor
        return Temp; //On retourne la temperature
    }
    public String cursorToTemp18(Cursor c){
        c.moveToFirst(); //on se place sur le premier élément
        String Temp;
        Temp = c.getString(NUM_COL_TEMP18);
        c.close(); //On ferme le cursor
        return Temp; //On retourne la temperature
    }
    public String cursorToTemp00(Cursor c){
        c.moveToFirst(); //on se place sur le premier élément
        String Temp;
        Temp = c.getString(NUM_COL_TEMP24);
        c.close(); //On ferme le cursor
        return Temp; //On retourne la temperature
    }
    public List<String> getTempReleve (String date, String idlac){
        List<String> listeTemp = new ArrayList<>();
        Cursor c1 = db.rawQuery("SELECT Temp6, Temp12, Temp18, Temp00  FROM treleve WHERE Date = date AND IdLac = idlac", null);
        listeTemp.add(cursorToTemp6(c1));
        listeTemp.add(cursorToTemp12(c1));
        listeTemp.add(cursorToTemp18(c1));
        listeTemp.add(cursorToTemp00(c1));
        return listeTemp;
    }


    public Cursor getDataLac(){
        return db.rawQuery("SELECT * FROM tlac", null);
    }


    public Cursor getDataReleve(){
        return db.rawQuery("SELECT * FROM treleve", null);
    }


    public List<String> getAllNomLac() {
       List<String> listeNom = new ArrayList<>();
       Cursor c1 = db.rawQuery("SELECT NomLac FROM tlac", null);
       if(c1.moveToFirst()){
           do{
               listeNom.add(c1.getString(0));
           } while (c1.moveToNext());

       }
        c1.close();
        return listeNom;

    }
    public long dropLac()
    {
        return db.delete(TABLE_LAC, null, null);
    }

}
