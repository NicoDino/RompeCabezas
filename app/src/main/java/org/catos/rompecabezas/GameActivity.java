package org.catos.rompecabezas;

/**
 * Created by ecko on 08/09/16.
 */

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity{

    private TextView nombre;
    private Button vacio;
    private Button [] [] matriz = new Button[4][4];

    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.acticity_game);

        //Localizar los controles
        this.nombre = (TextView)findViewById(R.id.nombre);
        /**
        for(int i=0; i<4; i++){
            for(int j=0;j<4; j++){
                this.matriz [i][j] = (Button)findViewById(R.id.b1);
            }
        }**/
        //lo siguiente intentar hacerlo en el for de arriba
        this.matriz [0][0] = (Button)findViewById(R.id.b1);
        this.matriz [0][1] = (Button)findViewById(R.id.b2);
        this.matriz [0][2] = (Button)findViewById(R.id.b3);
        this.matriz [0][3] = (Button)findViewById(R.id.b4);
        this.matriz [1][0] = (Button)findViewById(R.id.b5);
        this.matriz [1][1] = (Button)findViewById(R.id.b6);
        this.matriz [1][2] = (Button)findViewById(R.id.b7);
        this.matriz [1][3] = (Button)findViewById(R.id.b8);
        this.matriz [2][0] = (Button)findViewById(R.id.b9);
        this.matriz [2][1] = (Button)findViewById(R.id.b10);
        this.matriz [2][2] = (Button)findViewById(R.id.b11);
        this.matriz [2][3] = (Button)findViewById(R.id.b12);
        this.matriz [3][0] = (Button)findViewById(R.id.b13);
        this.matriz [3][1] = (Button)findViewById(R.id.b14);
        this.matriz [3][2] = (Button)findViewById(R.id.b15);
        this.matriz [3][3] = (Button)findViewById(R.id.b16);
        this.vacio = this.matriz[3][3];

        //Recuperamos la información pasada en el intent
        Bundle bundle = this.getIntent().getExtras();
        String n = bundle.getString("NOMBRE");

        //Construimos el mensaje a mostrar
        this.nombre.setText("Player: "+n);

        /**el siguiente metodo debe ser implementado de la misma forma para todos los botones de la matriz
         * por eso no debe ser "this.b1..." consultar cmo se implementa igual para todos.
         * Y deberia analizar si el vacio es alguno de sus vecinos, si es mover la imagen a el y setear el boton
         * desde el que se invocó como vacio.
        this.b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });**/
        View.OnClickListener act = new View.OnClickListener(){
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            public void onClick(View v){
                vacio.setBackground(matriz[0][0].getBackground());
                matriz[0][0].setBackground(null);
                vacio = matriz [0][0];
            }
        };

        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                this.matriz[i][j].setOnClickListener(act);
            }
        }
    }
}