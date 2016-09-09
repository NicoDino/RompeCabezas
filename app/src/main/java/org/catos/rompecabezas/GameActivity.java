package org.catos.rompecabezas;

/**
 * Created by ecko on 08/09/16.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity{

    private TextView nombre;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.acticity_game);

        //Localizar los controles
        this.nombre = (TextView)findViewById(R.id.nombre);

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        String n = bundle.getString("NOMBRE");

        //Construimos el mensaje a mostrar
        this.nombre.setText("Player: "+n);
    }

}