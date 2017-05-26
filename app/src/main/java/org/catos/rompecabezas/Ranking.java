package org.catos.rompecabezas;

import android.content.ContentValues;

/**
 * Created by ndinolfo on 20/5/2017.
 */

public class Ranking {
    private String nombre;
    private int movimientos;

    public Ranking(String nombre, int movimientos){
        this.nombre = nombre;
        this.movimientos=movimientos;
    }

    public String getNombre(){
        return nombre;
    }

    public int getMovimientos(){
        return movimientos;
    }


    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(RankingContract.RankingEntry.NOMBRE, nombre);
        values.put(RankingContract.RankingEntry.MOVIMIENTOS, movimientos);

        return values;
    }
}
