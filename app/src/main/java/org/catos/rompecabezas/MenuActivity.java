package org.catos.rompecabezas;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

    MediaPlayer sonidito;
    private Button start;
    private Button rank;
    private Button exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        //Localizar los controles
        this.start = (Button)findViewById(R.id.empezar);
        this.rank = (Button)findViewById(R.id.verRank);
        this.exit = (Button)findViewById(R.id.salir);

        //Implementamos el evento click del botón "EMPEZAR"
        this.start.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Creamos el intent
                sonidito= MediaPlayer.create(MenuActivity.this, R.raw.metalgearsolid);
                sonidito.start();
                Intent intentForm = new Intent(MenuActivity.this, FormActivity.class);
                //iniciamos la nueva actividad
                startActivity(intentForm);
            }
        });

        //Implementamos el evento click del botón "VER RANKING"
        this.rank.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Creamos el intent
                Intent intentR = new Intent(MenuActivity.this, RankActivity.class);
                //iniciamos la nueva actividad
                startActivity(intentR);
            }
        });

        //Implementamos el evento click del botón "SALIR"
        this.exit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
    }
}
