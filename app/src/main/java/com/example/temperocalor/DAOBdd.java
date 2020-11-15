package com.example.temperocalor;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOBdd {
    static final int VERSION_BDD =4;
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
    static final String COL_DATER = "DateR";
    static final int NUM_COL_DATER = 1;
    static final String COL_TEMP6 = "Temp6";
    static final int NUM_COL_TEMP6 = 2;
    static final String COL_TEMP12 = "Temp12";
    static final int NUM_COL_TEMP12 = 3;
    static final String COL_TEMP18 = "Temp18";
    static final int NUM_COL_TEMP18 = 4;
    static final String COL_TEMP24 = "Temp24";
    static final int NUM_COL_TEMP24 = 5;
    static final String COL_IDLACR = "IdLacr";
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
        values.put(COL_DATER, unReleve.getDate());
        values.put(COL_TEMP6, unReleve.getTemp6h());
        values.put(COL_TEMP12, unReleve.getTemp12h());
        values.put(COL_TEMP18, unReleve.getTemp18h());
        values.put(COL_TEMP24, unReleve.getTemp24h());
        values.put(COL_IDLACR, unReleve.getIdLacr());
        //on insère l'objet dans la BDD via le ContentValues
        return db.insert(TABLE_RELEVE, null, values);
    }
    private Lac cursorToLac(Cursor c){ //Cette méthode permet de convertir un cursor en un client
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;
        //Sinon
        c.moveToFirst(); //on se place sur le premier élément
        Lac unLac = new Lac(null,0,0 ); //On créé un client
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
        Releve unReleve = new Releve(null,null,null, null, null, null); //On créé un client
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        unReleve.setIdReleve(c.getString(NUM_COL_IDRELEVE));
        unReleve.setDate(c.getString(NUM_COL_DATER));
        unReleve.setTemp6h(c.getString(NUM_COL_TEMP6));
        unReleve.setTemp12h(c.getString(NUM_COL_TEMP12));
        unReleve.setTemp18h(c.getString(NUM_COL_TEMP18));
        unReleve.setTemp24h(c.getString(NUM_COL_TEMP24));
        unReleve.setIdLacr(c.getString(NUM_COL_IDLACR));
        c.close(); //On ferme le cursor
        return unReleve; //On retourne le client
    }

    public Lac getLacWithNomLac(String nom){
        //Récupère dans un Cursor les valeurs correspondant à un article grâce à sa designation
        Cursor c = db.query(TABLE_LAC, new String[]
                {COL_IDLAC,COL_NOMLAC, COL_COORDX, COL_COORDY, COL_NOMLAC}, COL_NOMLAC + " =\"" + nom +"\"", null, null, null, null);
        return cursorToLac(c);
    }

    /*public String getIdFromNom(String Nom){

        Cursor c = db.query(TABLE_LAC, new String[]
                {COL_IDLAC}, COL_NOMLAC + " =\"" + Nom +"\"", null, null, null, null);
        return cursorToId(c);

    }*/
    public String getIdFromNom(String Nom) {
        String Id = "";
        Cursor c = db.rawQuery("SELECT * FROM tlac WHERE NomLac = "+"'"+Nom+"'", null);
        if(c.moveToFirst()) {
            Id=c.getString(0);
        }
        c.close();
        return Id;
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
                {COL_IDRELEVE,COL_DATER, COL_TEMP6, COL_TEMP12, COL_TEMP18, COL_TEMP24, COL_IDLACR}, COL_IDRELEVE + " =\"" + id +"\"", null, null, null, null);
        return cursorToReleve(c);
    }
    public String cursorToTemp6(Cursor c){
        c.moveToFirst(); //on se place sur le premier élément
        String Temp;
        Temp = c.getString(0);
        //c.close(); //On ferme le cursor
        return Temp; //On retourne la temperature
    }
    public String cursorToTemp12(Cursor c){
        c.moveToFirst(); //on se place sur le premier élément
        String Temp;
        Temp = c.getString(1);
        //c.close(); //On ferme le cursor
        return Temp; //On retourne la temperature
    }
    public String cursorToTemp18(Cursor c){
        c.moveToFirst(); //on se place sur le premier élément
        String Temp;
        Temp = c.getString(2);
        //c.close(); //On ferme le cursor
        return Temp; //On retourne la temperature
    }
    public String cursorToTemp00(Cursor c){
        c.moveToFirst(); //on se place sur le premier élément
        String Temp;
        Temp = c.getString(3);
        //c.close(); //On ferme le cursor
        return Temp; //On retourne la temperature
    }
    public String getTemp6Releve (String date, String idlac){
        String Temp6 = "0";
        Cursor c1 = db.rawQuery("SELECT Temp6, Temp12, Temp18, Temp24 FROM treleve WHERE DateR = "+ "'"+date+"'"+ " AND IdLacr = " +idlac , null);
        if (c1.moveToFirst()) {
            Temp6 = cursorToTemp6(c1).toString();
        }
        c1.close();

        return Temp6;
    }
    public String getTemp12Releve (String date, String idlac){
        String Temp12 = "0";
        Cursor c1 = db.rawQuery("SELECT Temp6, Temp12, Temp18, Temp24 FROM treleve WHERE DateR = "+ "'"+date+"'"+ " AND IdLacr = " +idlac , null);
        if (c1.moveToFirst()) {
            Temp12 = cursorToTemp12(c1).toString();
        }
        c1.close();

        return Temp12;
    }
    public String getTemp18Releve (String date, String idlac){
        String Temp18 = "0";
        Cursor c1 = db.rawQuery("SELECT Temp6, Temp12, Temp18, Temp24 FROM treleve WHERE DateR = "+ "'"+date+"'"+ " AND IdLacr = " +idlac , null);
        if (c1.moveToFirst()) {
            Temp18 = cursorToTemp18(c1).toString();
        }
        c1.close();

        return Temp18;
    }
    public String getTemp00Releve (String date, String idlac){
        String Temp00 = "0";
        Cursor c1 = db.rawQuery("SELECT Temp6, Temp12, Temp18, Temp24 FROM treleve WHERE DateR = "+ "'"+date+"'"+ " AND IdLacr = " +idlac , null);
        if (c1.moveToFirst()) {
            Temp00 = cursorToTemp00(c1).toString();
        }
        c1.close();

        return Temp00;
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
    public List<String> getAllLac() {
        List<String> listeNom = new ArrayList<>();
        Cursor c1 = db.rawQuery("SELECT * FROM tlac", null);
        if(c1.moveToFirst()){
            do{
                listeNom.add(c1.getString(0));
            } while (c1.moveToNext());

        }
        c1.close();
        return listeNom;

    }
    public void dropLac()
    {
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_LAC + "'");
        db.execSQL("delete from " + TABLE_LAC);

    }
    public void dropRel()
    {
        db.execSQL("DELETE FROM SQLITE_SEQUENCE WHERE NAME = '" + TABLE_RELEVE + "'");
        db.execSQL("delete from " + TABLE_RELEVE);

    }
    public void InsertTemp6(String Date, String Id, String Temp)
    {
        db.execSQL("UPDATE treleve SET Temp6 = " + "'"+ Temp +"'" + " WHERE DateR = " + "'" + Date + "'" + " AND IdLacR =" + "'" + Id + "'");

    }
    public void InsertTemp12(String Date, String Id, String Temp)
    {
        db.execSQL("UPDATE treleve SET Temp12 = " + "'"+ Temp +"'" + " WHERE DateR = " + "'" + Date + "'" + " AND IdLacR =" + "'" + Id + "'");

    }

    public void InsertTemp18(String Date, String Id, String Temp)
    {
        db.execSQL("UPDATE treleve SET Temp18 = " + "'"+ Temp +"'" + " WHERE DateR = " + "'" + Date + "'" + " AND IdLacR =" + "'" + Id + "'");

    }

    public void InsertTemp00(String Date, String Id, String Temp)
    {
        db.execSQL("UPDATE treleve SET Temp24 = " + "'"+ Temp +"'" + " WHERE DateR = " + "'" + Date + "'" + " AND IdLacR =" + "'" + Id + "'");

    }



}
