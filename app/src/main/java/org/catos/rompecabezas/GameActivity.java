package org.catos.rompecabezas;

/**
 * Created by ecko on 08/09/16.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity{

    private TextView nombre;
    private Button b1, b2, vacio;

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.acticity_game);

        //Localizar los controles
        this.nombre = (TextView)findViewById(R.id.nombre);
        this.b1 = (Button)findViewById(R.id.b1);
        this.b2 = (Button)findViewById(R.id.b2);
        this.vacio = b2;

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        String n = bundle.getString("NOMBRE");

        //Construimos el mensaje a mostrar
        this.nombre.setText("Player: "+n);

        this.b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });
    }

}