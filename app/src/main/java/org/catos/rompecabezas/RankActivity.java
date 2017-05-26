package org.catos.rompecabezas;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ndinolfo on 18/5/2017.
 */

public class RankActivity extends Activity{

    private SqlHelper myDb;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        this.myDb = new SqlHelper(this);
        String result = myDb.getRanking();
        TextView puntajes;
        puntajes = (TextView) findViewById(R.id.puntajes);
        puntajes.setText(result);
    }

    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
    }
}
