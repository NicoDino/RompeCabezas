package org.catos.rompecabezas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by ndinolfo on 20/5/2017.
 */

public class SqlHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "App.db";
    public SqlHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + RankingContract.RankingEntry.TABLE_NAME + "("
                + RankingContract.RankingEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + RankingContract.RankingEntry.NOMBRE + " TEXT NOT NULL,"
                + RankingContract.RankingEntry.MOVIMIENTOS + " TEXT NOT NULL);"
        );
    }

    public long mockRanking(SQLiteDatabase db, Ranking ranking){
        return db.insert(
                RankingContract.RankingEntry.TABLE_NAME,
                null,
                ranking.toContentValues());
    }

    public long saveRanking(Ranking ranking) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RankingContract.RankingEntry.NOMBRE, ranking.getNombre());
        values.put(RankingContract.RankingEntry.MOVIMIENTOS, ranking.getMovimientos());
        return sqLiteDatabase.insert(
                RankingContract.RankingEntry.TABLE_NAME,
                null,
                values);

    }

    public String getRanking(){
        String result="";
        SQLiteDatabase db = this.getReadableDatabase();
        int i = 1;
        Cursor c = db.query(
                RankingContract.RankingEntry.TABLE_NAME,  // Nombre de la tabla
                null,  // Lista de Columnas a consultar
                null,  // Columnas para la cláusula WHERE
                null,  // Valores a comparar con las columnas del WHERE
                null,  // Agrupar con GROUP BY
                null,  // Condición HAVING para GROUP BY
                RankingContract.RankingEntry.MOVIMIENTOS  // Cláusula ORDER BY
        );

        while(c.moveToNext()){
            String name = c.getString(c.getColumnIndex(RankingContract.RankingEntry.NOMBRE));
            String movimientos = c.getString(c.getColumnIndex(RankingContract.RankingEntry.MOVIMIENTOS));
            result += i++ + "\t\t"+ name + "\t" + movimientos + "\n\n";
        }
        return result;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
