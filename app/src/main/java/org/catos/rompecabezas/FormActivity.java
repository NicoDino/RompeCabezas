package org.catos.rompecabezas;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import android.app.Activity;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by root on 08/09/16.
 */
public class FormActivity extends Activity {
    MediaPlayer ps1;
    MediaPlayer chanchan;
    private EditText nombre;
    private Button jugar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        ps1= MediaPlayer.create(FormActivity.this, R.raw.ps1);
        ps1.start();
        //Localizar los controles
        this.nombre = (EditText) findViewById(R.id.nombre);
        this.jugar = (Button)findViewById(R.id.jugar);

        //Implementamos el evento click del botón
        this.jugar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                try {
                    if (ps1.isPlaying()) {
                        ps1.stop();
                        ps1.release();
                    }
                }catch(Exception e){}
                chanchan= MediaPlayer.create(FormActivity.this, R.raw.chanchan);
                chanchan.start();
                String aux = nombre.getText().toString();
                //si no seingresa nada en el EditText no se puede comenzar a1 jugar
                if(!aux.equals("") && aux != null) {
                    //Creamos el intent
                    Intent intentGame = new Intent(FormActivity.this, GameActivity.class);

                    //Creamos el bundle para pasar info entre las actividades
                    Bundle b = new Bundle();
                    b.putString("NOMBRE", aux);

                    //Añadimos la info al intentGame
                    intentGame.putExtras(b);


                    //iniciamos la nueva actividad
                    startActivity(intentGame);
                    finish();
                }
            }
        });

    }

    @Override
    public void onBackPressed(){
        finish();
        super.onBackPressed();
    }

}