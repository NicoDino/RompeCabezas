package org.catos.rompecabezas;

import android.content.ContentValues;
import android.provider.BaseColumns;

/**
 * Created by ndinolfo on 20/5/2017.
 */

public class RankingContract {

    private RankingContract(){}

    public static abstract class RankingEntry implements BaseColumns{
        public static final String TABLE_NAME = "ranking";
        public static final String NOMBRE = "nombre";
        public static final String MOVIMIENTOS = "movimientos";


    }
}
